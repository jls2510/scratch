<?php
/**
  * Zend Framework (http://framework.zend.com/)
  *
  * @link http://github.com/zendframework/ZendSkeletonApplication for
the canonical source repository
  * @copyright Copyright (c) 2005-2015 Zend Technologies USA Inc.
(http://www.zend.com)
  * @license   http://framework.zend.com/license/new-bsd New BSD License
  */

namespace Application\Controller;

use Application\Model\Organization;
use Application\Util\AbstractRestController;
use phpseclib\Crypt\RSA;
use Zend\Http\Headers;
use Zend\Http\Response;
use Zend\Session\Container;
use Zend\View\Model\JsonModel;


class LoginController extends AbstractRestController
{
     const KEY = '-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzr1DshzrBVNtRClyOQSj
f3xOHB1C9I+aFnJufM6teHLvnUZAj+IMA2FUvkahZ1V25+x8W4XuhYF/4/i7nOow
rggg2LqSh9RaKDf4oco4nPGme3HftVwBoG4Z+PMy28ZaTZIhfIQUv98XPzG4ReMP
jjqP8mN5OL2wnPfeqmCRJjBPpfNodKswOT60ph6n8+fttqwOROiSY7QFD89oGXyl
zPMvQ07HPbBTJvD+BVB065vRzQLe0xW5wzg5Qla9T0S6GOEy30S9OGhM2zewthzi
Y2SE37ECLayn1xqQ9fUvE1Sem1hxDYhfKCNUfqSoUlKAbXx22OtpdYRe2VhR95HN
ZwIDAQAB
-----END PUBLIC KEY-----';

     const USER = 'erpSynch';
     protected $pg;

     /**
      * @return mixed
      * @throws \Zend\Session\Exception\InvalidArgumentException
      */
     public static function getUserId()
     {
         $session = new Container(self::USER);

         return $session->offsetGet('userId');
     }

     /**
      * @return mixed
      * @throws \Zend\Session\Exception\InvalidArgumentException
      */
     public static function getOrgId()
     {
         $session = new Container(self::USER);

         return $session->offsetGet('orgId');
     }

     public static function getOrgName()
     {
         $session = new Container(self::USER);

         return $session->offsetGet('orgName');
     }

     /**
      * @return mixed
      * @throws \Zend\Session\Exception\InvalidArgumentException
      */
     public static function getWinOrgId()
     {
         $session = new Container(self::USER);

         return $session->offsetGet('winOrgId');
     }

     /**
      * @return bool
      * @throws \Zend\Session\Exception\InvalidArgumentException
      */
     public static function isAuthenticated()
     {
         $session = new Container(self::USER);

         return $session->offsetExists('loggedIn') &&
$session->offsetExists('userId') && $session->offsetGet('loggedIn') ===
true;
     }

     /**
      * @param mixed $id
      * @return mixed|Response|JsonModel
      * @throws \Exception
      */
     public function get($id)
     {
         $json = new JsonModel();
         $response = [];
         if (getenv('APP_ENV') === 'development' &&
substr_count($this->getRequest()
->getUri()
->getPath(), 'token') < 1) {
             $testOrgId = $id;
             if ($testOrgId !== null) {
                 $decrypt =
                     json_encode([
                         'orgId' => $testOrgId,
                         'u'     => 'xavier.amella@kogentservices.com',
//                        'o'     => 'S-17-0027220',
//                        'o'     => '',
                     ]);

                 if ($this->login($decrypt)) {
                     return $this->redirect()
                                 ->toRoute('home');
                 }
             }
         }

         $whitelist = [
             'http://generic-test-server/',
             'http://localhost:1841/',
             'https://winjittestsp.gowithwin.com/',
             'https://sp.gowithwin.com/',
             'https://synch.gowithwin.com/'
         ];

         /**
          * If we are logging in through token URL from a white list,
reset the session id to the already logged in one
          */
         if (@\in_array($_SERVER['HTTP_REFERER'], $whitelist, true)) {
             $token = $id;
             session_id($token);
             session_start();
             $response['success'] = true;
         } else {
             if (substr_count($this->getRequest()
                                   ->getUri()
                                   ->getPath(), 'token') > 1) {
                 return self::notAuthorizedResponse();
             }
         }

         $json->setVariables($response);

         return $json;

     }

     /**
      * @param mixed $data
      * @return mixed|Response
      * @throws \Exception
      */
     public function create($data)
     {
         $token = $data['t'];
//        $token =
'ycRpalT2fAInlgUQ3FwqWsWwWnZbCGne85YeHNDrPTi4BP7EPpd\/LqrylF2LSDsE4mEjhSJRwGmYzjKb+xTHAgsQthNrkN\/q7lMCko9CJ3ZKQnjdm1D6wBRnQ16j+RvpOrOaq72OYQ9Aw1YQMZjZdNrozgmHysyw5CSANf1GEz3ok10wCLBx9zLk2Z4Ded+JpTEeGX2FrOR9+AXHIKl32ANHLqEQAt107CnYCcYmAXtLZ9sjTRB9BpzzWBebwkyhu+RUHfqKkJX1O9cFNrCdjDC3eA+Dd8oC37feCGueNZ5a4QLtpG0ulzQFYVi\/XkO6sZBA0RblIRrabyI+4VgwDw==';
//        $token =
'oIU7WTWWgDAJ0Rtx2eOZ\/C9wLWVqxaMqon\/RrnczWoRUSUahcHFtyZ9nd2vlMMvLZF2JYB64kAjIk6mbA3Nq81Jxn6pAJlpaGVQ28jgrLnLZiR\/vFQJWpt+Qp3hIH0rqge0xgozmoX0nxDN71p26Aq\/6joZQJCP26NoQsfI39r1W+9cDw2R50+ubYZB9ZKn0q+60tTH8SvPS7ndXmC9YE4ZMaGzPXYDue0+0VKmUfqT2vRj3XXShbjw5XHK9dmAXAt7FMPJPFagNhxZTefYPXYP2bVO\/RTLxGFQb74qVr8+3ImnzO5DPYMGCk26QxXeLDSeRckeD0OXz\/3nYaylHEg==';
//        $token =
't8E/Vjzd0rs4tZdBeumrIcEp8TnOCZdnhRiaYicclS+kWx1hlsRjaLLzdPIWouX9p36xsN9UcZPAYe01v6J5+YVKYBJoKd4nlMdEa7m72N7ayMVl4LNyd6sxq20ZKEzp06AwSilPivOYTaJrd9pRUNFTa2mF9ukKhqtzqCme+38LCUewX5onOTarnSfKHmk9Xw90ESx/WUubRAYmOqn5bu19ky2V+Fxx4SFaBGiXEDtTKgHdU1BQQUgKoEUSWWLiCMAwQtjeqdmNu7s4Zy2o5JkQdCFUzk6l7jbJb2B5ZPHbtCn05TWicwytoni+PrKjggy+d8ibP08ng0b3th2jKA==';

         if ($token !== null) {
             $decrypt = $this->decrypt($token);
//            $decrypt = '{"orgId": 1052}';
             if ($decrypt !== false) {

                 if ($this->login($decrypt)) {
                     return $this->redirect()
                                 ->toRoute('home');
                 }

                 return $this->redirect()
                             ->toRoute('marketing');
             }
         }

         return $this->redirect()
                     ->toRoute('marketing');
     }

     /**
      * @param $json
      * @return bool
      * @throws \Exception
      */
     public function login($json)
     {
         if (!$this->session) {
             $this->setSession(new Container(self::USER));
         }

         $winObject = json_decode($json);
         $user = $this->getSession();
         $user->offsetSet('winOrgId', $winObject->orgId);
         $user->offsetSet('userId', $winObject->u);
         $user->offsetSet('loggedIn', true);

         if (isset($winObject->o) && $winObject->o != '') {
             $user->offsetSet('winOrderNumber', $winObject->o);
         } else {
             $user->offsetSet('winOrderNumber', '');
         }

         $org = new Organization($this->getPg());
         try {
             $org->get([
                 'winOrgId' => $user->offsetGet('winOrgId'),
             ]);
             $user->offsetSet('orgId', $org->getId());
             $user->offsetSet('orgName', $org->getName());

             return true;
         } catch (\Exception $e) {
             return false;
         }
     }

     /**
      * @return mixed
      */
     public function getPg()
     {
         return $this->pg;
     }

     /**
      * @return Response
      * @throws \Zend\Http\Exception\InvalidArgumentException
      * @throws \Zend\Http\Exception\RuntimeException
      */
     public static function notAuthorizedResponse()
     {
         $unAuthed = [
             'success' => false,
             'message' => 'Unauthorized',
             'code'    => 401,
         ];

         $response = new Response();
$response->setHeaders(Headers::fromString('Content-Type:
application/json'));
         $response->setContent(json_encode($unAuthed));
         $response->setStatusCode(401);

         return $response;
     }

     /**
      * @inject postgres
      * @param mixed $pg
      * @return LoginController
      */
     public function setPg($pg)
     {
         $this->pg = $pg;

         return $this;
     }

     /**
      * @return mixed|Response
      * @throws \Exception
      */
     public function createAction()
     {
         return $this->create($this->params()
                                   ->fromPost());
     }

     /**
      * @param $token
      * @return string
      */
     private function decrypt($token)
     {
         $crypt = new RSA();
$crypt->setEncryptionMode(RSA::ENCRYPTION_PKCS1);
         $crypt->loadKey(self::KEY);
         return $crypt->decrypt(base64_decode($token));
     }
}

