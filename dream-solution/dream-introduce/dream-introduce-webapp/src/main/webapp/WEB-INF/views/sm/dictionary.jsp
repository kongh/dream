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
	    	seajs.use("app-dictionary");
        </script>
    </head>
    <body class="skin-blue">
    <div class="wrapper row-offcanvas row-offcanvas-left">
            <!-- Right side column. Contains the navbar and content of the page -->
            <aside class="right-side strech">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                       	字典配置
                        <small>字典配置</small>
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-solid">
                                <div class="box-header">
 								<%@ include file="/WEB-INF/views/sm/dictionary/toolbar.jsp" %>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="sm-dictionary-table" class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                            	<th>序号</th>
                                            	<th>ID</th>
                                                <th>字典名称</th>
                                                <th>字典编码</th>
                                                <th>备注</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                        <tfoot>
                                        </tfoot>
                                    </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
                    
                </section><!-- /.content -->
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <%@ include file="/WEB-INF/views/sm/dictionary/create.jsp" %>
		<%@ include file="/WEB-INF/views/sm/dictionary/update.jsp" %>
    </body>
</html>