<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="/WEB-INF/views/frame/title.jsp" %>
    	<%@ include file="/WEB-INF/views/frame/meta.jsp" %>
        <%@ include file="/WEB-INF/views/frame/ie-html5-support.jsp" %>
        <%@ include file="/WEB-INF/views/frame/seajs-config.jsp" %>
        <script type="text/javascript">
	    	seajs.use("app-login");
        </script>
    </head>
    <body class="skin-blue">
		<!-- header logo: style can be found in header.less -->
        <header class="header">
            <a href="#" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
                IDream
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">切换</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                </div>
            </nav>
        </header>
        <div class="container-fluid">
		  <div class="row">
		   <div class="col-xs-4"></div>
		    <div class="col-xs-4">
		    <br>
		    <br>
		    <br>
		    <br>
		    <br>
		    <br>
		    <br>
		    <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">登录梦想基础平台</h3>
                                </div><!-- /.box-header -->
                                <!-- form start -->
                                <form id="login-form" role="form" action="${ctx}/login/auth" method="post">
                                    <div class="box-body">
                                        <div class="input-group">
                                        <span class="input-group-addon">@</span>
                                        <input type="text" name="account" class="form-control"  value="${account}" placeholder="用户名">
                                    </div>
                                    <br/>
                                    <div class="input-group">
                                        <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                        <input type="password" name="password" class="form-control" placeholder="密码">
                                    </div>
                                    </div><!-- /.box-body -->

                                    <div class="box-footer">
                                    	<div class="alert alert-danger  coder-margin-left <c:if test="${empty errorMsg}">hidden</c:if>" role="alert" >
                                    	${errorMsg}
                                    	</div>
                                        <button type="submit" class="btn btn-primary btn-lg btn-block">登录</button>
                                    </div>
                                </form>
                            </div><!-- /.box -->
    		</div>
    		 <div class="col-xs-4"></div>
		  </div>
		</div>
    </body>
</html>