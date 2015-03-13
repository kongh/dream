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
	    	seajs.use("app-dictionary-item");
        </script>
    </head>
    <body class="skin-blue">
    <div class="wrapper row-offcanvas row-offcanvas-left">
            <aside class="right-side strech">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                       	字典配置
                        <small>数据</small>
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="box box-solid">
                                <div class="box-header">
                                    <%@ include file="/WEB-INF/views/sm/dictionary/item/toolbar.jsp" %>
                                </div><!-- /.box-header -->
                                <div class="box-body table-responsive">
                                    <table id="sm-dictionary-item-table" class="table table-bordered">
								        <thead>
								          <tr>
								            <th>数据名称</th>
								            <th>数据值</th>
								            <th>备注</th>
								          </tr>
								        </thead>
								        <tbody>
								          <%@ include file="/WEB-INF/views/sm/dictionary/item/root.jsp" %>
								        </tbody>
								       </table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
                        </div>
                    </div>
     		  </section><!-- /.content -->
         </aside><!-- /.right-side -->
    </div><!-- ./wrapper -->
    <%@ include file="/WEB-INF/views/sm/dictionary/item/create.jsp" %>
	<%@ include file="/WEB-INF/views/sm/dictionary/item/update.jsp" %>
    </body>
</html>