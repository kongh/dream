<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/layouts/taglib.jsp" %>

            <!-- Left side column. contains the logo and sidebar -->
            <aside class="left-side sidebar-offcanvas">                
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <img src="${ctx}/static/admin/img/avatar3.png" class="img-circle" alt="User Image" />
                        </div>
                        <div class="pull-left info">
                            <p>欢迎, ${user.account}</p>
                            <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                    	<c:forEach items="${adminMenus}" var="menu">
                    	   <li class="treeview">
                            <a href="#">
                                <i class="fa fa-bar-chart-o"></i>
                                <span>${menu.name }</span>
                                <i class="fa fa-angle-left pull-right"></i>
                            </a>
                            <ul class="treeview-menu">
                            	<c:forEach items="${menu.data}" var="subMenu">
                                	<li><a href="javascript:void(0);" data-load-frame-url="${ctx}${subMenu.url}"><i class="fa fa-angle-double-right"></i> ${subMenu.name}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    	</c:forEach>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
