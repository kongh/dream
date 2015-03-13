<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>梦想管理平台</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width">

  <link rel="stylesheet" href="${ctx}/static/browser/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/static/browser/css/bootstrap-responsive.min.css">
  <link rel="stylesheet" href="${ctx}/static/browser/css/font-awesome.min.css">
  <link rel="stylesheet" href="${ctx}/static/browser/css/main.css">
  <link rel="stylesheet" href="${ctx}/static/browser/css/sl-slide.css">

  <script src="${ctx}/static/browser/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

  <!-- Le fav and touch icons -->
  <link rel="shortcut icon" href="${ctx}/static/browser/images/ico/favicon.ico">
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${ctx}/static/browser/images/ico/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx}/static/browser/images/ico/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx}/static/browser/images/ico/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="${ctx}/static/browser/images/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>

  <!--Header-->
  <header class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a id="logo" class="pull-left" href="index.html"></a>
        <div class="nav-collapse collapse pull-right">
          <ul class="nav">
            <li><a href="about-us.html">平台管理</a></li>
            <li><a href="services.html">用户注册</a></li>
            <li class="login">
              <a data-toggle="modal" href="#loginForm"><i class="icon-lock"></i></a>
            </li>
          </ul>        
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </header>
  <!-- /header -->


  <section class="title">
    <div class="container">
      <div class="row-fluid">
        <div class="span6">
          <h1>用户登陆</h1>
        </div>
        <div class="span6">
          <ul class="breadcrumb pull-right">
           <!--  <li><a href="index.html">Home</a> <span class="divider">/</span></li>
            <li><a href="#">Pages</a> <span class="divider">/</span></li>
            <li class="active">Registration</li> -->
          </ul>
        </div>
      </div>
    </div>
  </section>
  <!-- / .title -->       


  <section id="login-page" class="container">
    <form class="center" action='${ctx}/login/auth' method="POST">
      <fieldset class="registration-form">
        <div class="control-group">
          <!-- Username -->
          <div class="controls">
          	用户名：
            <input type="text" id="account" name="account" placeholder="用户名" class="input-xlarge">
          </div>
        </div>

        <div class="control-group">
          <!-- Password-->
          <div class="controls">密&nbsp;&nbsp;&nbsp;码：
            <input type="password" id="password" name="password" placeholder="密码" class="input-xlarge">
          </div>
        </div>

        <div class="control-group">
          <!-- Button -->
          <div class="controls">
            <button class="btn btn-success btn-large btn-block">登陆</button>
          </div>
        </div>
      </fieldset>
    </form>
  </section>
  <!-- /#registration-page -->

<!--Footer-->
<footer id="footer">
    <div class="container">
        <div class="row-fluid">
            <div class="span5 cp">
                &copy; 2014 <a target="_blank" href="#" title="Dream group ">Dream Group</a>. All Rights Reserved.
            </div>
            <!--/Copyright-->

            <div class="span6">
                <ul class="social pull-right">
                    <!-- <li><a href="#"><i class="icon-facebook"></i></a></li>
                    <li><a href="#"><i class="icon-twitter"></i></a></li>
                    <li><a href="#"><i class="icon-pinterest"></i></a></li>
                    <li><a href="#"><i class="icon-linkedin"></i></a></li>
                    <li><a href="#"><i class="icon-google-plus"></i></a></li>                       
                    <li><a href="#"><i class="icon-youtube"></i></a></li>
                    <li><a href="#"><i class="icon-tumblr"></i></a></li>                        
                    <li><a href="#"><i class="icon-dribbble"></i></a></li>
                    <li><a href="#"><i class="icon-rss"></i></a></li>
                    <li><a href="#"><i class="icon-github-alt"></i></a></li>
                    <li><a href="#"><i class="icon-instagram"></i></a></li>    -->                
                </ul>
            </div>

            <div class="span1">
                <a id="gototop" class="gototop pull-right" href="#"><i class="icon-angle-up"></i></a>
            </div>
            <!--/Goto Top-->
        </div>
    </div>
</footer>
<!--/Footer-->

<!--  Login form -->
<div class="modal hide fade in" id="loginForm" aria-hidden="false">
    <div class="modal-header">
        <i class="icon-remove" data-dismiss="modal" aria-hidden="true"></i>
        <h4>Login Form</h4>
    </div>
    <!--Modal Body-->
    <div class="modal-body">
        <form class="form-inline" action="index.html" method="post" id="form-login">
            <input type="text" class="input-small" placeholder="Email">
            <input type="password" class="input-small" placeholder="Password">
            <label class="checkbox">
                <input type="checkbox"> Remember me
            </label>
            <button type="submit" class="btn btn-primary">Sign in</button>
        </form>
        <a href="#">Forgot your password?</a>
    </div>
    <!--/Modal Body-->
</div>
<!--  /Login form -->

<script src="${ctx}/static/browser/js/vendor/jquery-1.9.1.min.js"></script>
<script src="${ctx}/static/browser/js/vendor/bootstrap.min.js"></script>
<script src="${ctx}/static/browser/js/main.js"></script>

</body>
</html>
