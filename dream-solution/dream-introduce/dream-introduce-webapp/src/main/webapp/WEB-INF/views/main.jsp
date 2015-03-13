<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="/WEB-INF/views/frame/title.jsp" %>
    	<%@ include file="/WEB-INF/views/frame/meta.jsp" %>
        <%@ include file="/WEB-INF/views/frame/ie-html5-support.jsp" %>
        <%@ include file="/WEB-INF/views/frame/seajs-config.jsp" %>
    </head>
    <body class="skin-blue">
    	<div class="container-fluid">
		  <div class="row">
		    <div class="col-xs-6 col-xs-offset-6">
    			<a href="#" tabindex="0" id="main-popover"  role="button" data-toggle="popover" data-placement="bottom" data-trigger="manual" title="操作提示" data-content="未设置提示内容" ></a>
    		</div>
		  </div>
		</div>
    <%@ include file="/WEB-INF/views/frame/header.jsp" %>
        <%@ include file="/WEB-INF/views/frame/left.jsp" %>
                	<!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-solid">
                                <div class="box-header">
                                </div>
                           		<div class="box-body table-responsive" style="padding:0px;">
                           		<div class="embed-responsive embed-responsive-16by9">
								  <iframe id="content-frame" class="embed-responsive-item" src="${ctx}/sm/seajs" allowfullscreen></iframe>
								</div>
                           		</div>
                           	</div>
                        </div>
                    </div>
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
    	</div>
    	<div id="ajaxStatusErrorModal" class="modal fade">
		  <div class="modal-dialog modal-sm">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		        <h4 class="modal-title">操作提示</h4>
		      </div>
		      <div class="modal-body">
		        <p>你没有登录或者会话已经失效,请重新登陆!</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
    </body>
</html>