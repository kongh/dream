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
	    	seajs.use("app-resource");
        </script>
    </head>
    <body class="skin-blue">
    <div class="wrapper row-offcanvas row-offcanvas-left">
            <aside class="right-side strech">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                       	资源配置
                        <small>资源配置</small>
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-solid">
                                <div class="box-header">
                                    <%@ include file="/WEB-INF/views/sm/resource/toolbar.jsp" %>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="sm-resource-table" class="table table-bordered">
								        <thead>
								          <tr>
								            <th>资源名称</th>
								            <th>资源编码</th>
								            <th>资源类型</th>
								            <th>资源路径</th>
								            <th>资源图标</th>
								            <th>描述</th>
								          </tr>
								        </thead>
								        <tbody>
								          <%@ include file="/WEB-INF/views/sm/resource/root.jsp" %>
								        </tbody>
								       </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
     		  </section><!-- /.content -->
         </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
     <%@ include file="/WEB-INF/views/sm/resource/create.jsp" %>
	 <%@ include file="/WEB-INF/views/sm/resource/update.jsp" %>
    </body>
</html>