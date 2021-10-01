<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<sj:head jqueryui="true" jquerytheme="redmond"/>
<div>
	<s:form id="filterForm" action="searchBook">
		<s:textfield key="global.book.author" name="searchAuthor" />
		<s:textfield key="global.book.name" name="searchName" />
		<sj:submit value="Search" button="true" indicator="indicator" /> 
	</s:form>
    <s:url var="remoteurl" action="searchBookJson"/>
    <sjg:grid
    	id="gridtable"
        dataType="json"
        href="%{remoteurl}"
        gridModel="gridModel"
        loadonce="true"
        reloadTopics="reloadGrid" 
        formIds="filterForm"> 
        <sjg:gridColumn name="id" index="id" title="id" sortable="false"/>
        <sjg:gridColumn name="author" index="author" title="author" sortable="false"/>
        <sjg:gridColumn name="name" index="name" title="name" sortable="false"/>
     </sjg:grid>
</div>
