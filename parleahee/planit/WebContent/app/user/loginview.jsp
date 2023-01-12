<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
  <head>
  <meta charset="utf-8">
    <title>Plan.It</title>
    <style>
	@import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700,800,900');

body{
  font-family: 'Poppins', sans-serif;
  font-weight: 300;
  font-size: 15px;
  line-height: 1.7;
  color: #c4c3ca;
  background-color: #1f2029;
  overflow-x: hidden;
}
a {
  cursor: pointer;
  transition: all 200ms linear;
}
a:hover {
  text-decoration: none;
}
.link {
  color: #c4c3ca;
}
.link:hover {
  color: #ffeba7;
}
p {
  font-weight: 500;
  font-size: 14px;
  line-height: 1.7;
}
.login_title {
	width : 100%;
  font-weight: 600;
  font-size: 22px;
  text-align : center;
  line-height: 30px;
}
h6 span{
  padding: 0 20px;
  text-transform: uppercase;
  font-weight: 700;
}
.section{
  position: relative;
  width: 90%;
  padding : 0 2%;
  margin : 0 10px;
  display: block;
}
.full-height{
  min-height: 100vh;
}


.card-3d-wrap {
  position: relative;
  width: 100%;
  height: 400px;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  perspective: 800px;
  margin-top: 60px;
  margin-left: 0;
}
.card-3d-wrapper {
  width: 40%;
  height: 100%;
  margin : 10% auto;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  transition: all 600ms ease-out; 
}
.card-front, .card-back {
  width: 100%;
  height: 100%;
  background-color: #2a2b38;
  background-image: url('https://s3-us-west-2.amazonaws.com/s.cdpn.io/1462889/pat.svg');
  background-position: bottom center;
  background-repeat: no-repeat;
  background-size: 300%;
  position: absolute;
  border-radius: 6px;
  left: 0;
  top: 0;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
  -webkit-backface-visibility: hidden;
  -moz-backface-visibility: hidden;
  -o-backface-visibility: hidden;
  backface-visibility: hidden;
}
.card-back {
  transform: rotateY(180deg);
}

.center-wrap{
  position: absolute;
  width: 100%;
  padding: 0;
  top: 47%;
  left: 0;
  transform: translate3d(0, -50%, 35px) perspective(100px);
  z-index: 20;
  display: block;
}


.form-group{ 
  position: relative;
  width:100%;
  display: block;
   margin: 0 auto;
    padding: 0 3%;
    
  margin-bottom : 10px;
}
.form-style {
  padding: 13px 20px;
  padding-left: 30px;
  height: 38px;
  width: 85%;
  font-weight: 500;
  border-radius: 4px;
  font-size: 14px;
  line-height: 22px;
  letter-spacing: 0.5px;
  outline: none;
  color: #c4c3ca;
  background-color: #1f2029;
  border: none;
  -webkit-transition: all 200ms linear;
  transition: all 200ms linear;
  box-shadow: 0 4px 8px 0 rgba(21,21,21,.2);
}
.form-style:focus,
.form-style:active {
  border: none;
  outline: none;
  box-shadow: 0 4px 8px 0 rgba(21,21,21,.2);
}
.input-icon {
  position: absolute;
  top: 0;
  left: 18px;
  height: 48px;
  font-size: 24px;
  line-height: 48px;
  text-align: left;
  color: #ffeba7;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}

.form-group input:-ms-input-placeholder  {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.form-group input::-moz-placeholder  {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.form-group input:-moz-placeholder  {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.form-group input::-webkit-input-placeholder  {
  color: #c4c3ca;
  opacity: 0.7;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.form-group input:focus:-ms-input-placeholder  {
  opacity: 0;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.form-group input:focus::-moz-placeholder  {
  opacity: 0;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.form-group input:focus:-moz-placeholder  {
  opacity: 0;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.form-group input:focus::-webkit-input-placeholder  {
  opacity: 0;
  -webkit-transition: all 200ms linear;
    transition: all 200ms linear;
}
.login_btn_box{
	width: 70%;
	margin: 0 auto;
}
.btn{  
  margin : 8% auto 2%;
  border-radius: 4px;
  height: 44px;
  line-height : 44px;
  width : 49%;
  font-size: 13px;
  font-weight: 600;
  text-transform: uppercase;
  padding: 0 30px;
  letter-spacing: 1px;
  text-align: center;
  border: none;
  background-color: #ffeba7;
  color: #102770;
  box-shadow: 0 8px 24px 0 rgba(255,235,167,.2);
}
.btn:active,
.btn:focus{  
  background-color: #102770;
  color: #ffeba7;
  box-shadow: 0 8px 24px 0 rgba(16,39,112,.2);
}
.btn:hover{  
  background-color: #102770;
  color: #ffeba7;
  box-shadow: 0 8px 24px 0 rgba(16,39,112,.2);
}
.login_forgot{
	width: 70%;
	margin: 0 auto;
	text-align: center;
}
     video { width: 100%;
      max-width  : 100%; 
      height: 100%;
      size: cover;
      object-fit: fill;
		z-index: -999;
      position: fixed;
      bottom : 0px;
      left : 0px;
      }
      #black_box{
  	 width :100%;
  	 height : 100%;
  	 position: fixed;
  	 top: 0px;
  	 left: 0px;
  	 background-color: rgba(0,0,0,0.7);
  	 z-index: -599;
  	 }
  	 
    </style>
    	
  </head>
  <body>
			<video muted autoplay loop>
			     <source src="${cp}/images/v.mp4" type="video/mp4" >
		         <strong>Your browser does not support the video tag.</strong>
			</video>
	<div id="black_box"></div>
	<div class="sections">
    <div class="container">
      <div class="row full-height justify-content-center">
        <div class="col-12 text-center align-self-center py-5">
          <div class="sections pb-5 pt-5 pt-sm-2 text-center">
            <h6 class="mb-0 pb-3"><span>Log In </span><span>Sign Up</span></h6>
            <div class="card-3d-wrap mx-auto">
              <div class="card-3d-wrapper">
                <div class="card-front">
                  <div class="center-wrap">
                    <div class="section text-center">
                      <p class="login_title">Log In</p>
	                     <form action="${cp}/user/loginok.tc" name="loginForm" method="post">
	                      <div class="form-group">
	                        <input type="text" name="userid" class="form-style" placeholder="Your ID" >
	                        <i class="input-icon uil uil-at"></i>
	                      </div>  
	                      <div class="form-group mt-2">
	                        <input type="password" name="userpw" class="form-style" placeholder="Your Password" id="logpass" autocomplete="off">
	                        <i class="input-icon uil uil-lock-alt"></i>
	                      </div>
	                      <!-- <a href="#" class="btn mt-4">submit</a> -->
	                      <div class="login_btn_box">
	                      	<input type="submit" value="Login" class="btn">
	                      	<input type="button" value="Join" class="btn" onclick="javascript : location.href='${cp}/user/joinuser.tc'">
	                      </div>
	                     </form>
                                    <p class="login_forgot"><a href="#0" class="link">Forgot your password?</a></p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
      </div>
  </div>
</body>
</html>