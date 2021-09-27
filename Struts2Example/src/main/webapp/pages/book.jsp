<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<sj:head jqueryui="true" jquerytheme="redmond"/>
<script type="text/javascript">
<!--
	$.subscribe("reloadGrid", function(){
		alert("DF");
	   $("gridtable").trigger("reloadGrid");
	   alert("DF2");
	});
//-->
</script>
<div>
	<%-- submit return json data --%>
	<%-- a create too much recursion error --%>
	<%-- url action gridView2 : http://localhost:8080/Struts2GridExample//Struts2GridExample/gridView2.html --%>
	<%-- url action /gridView2 : http://localhost:8080/Struts2GridExample//Struts2GridExample//gridView2.html --%>
	<%-- action /gridView2 : http://localhost:8080/Struts2GridExample//gridView2.html --%>
	<s:form id="filterForm" action="searchBook">18
		<s:textfield key="global.book.author" name="searchAuthor" />
		<s:textfield key="global.book.name" name="searchName" />
		<sj:a
            value="Search"
            button="true"
            onClickTopics="reloadGrid"
            indicator="indicator" />
            
		<sj:submit
            value="Search"
            button="true"
            indicator="indicator" /> <%--onClickTopics="reloadGrid" --%>
	</s:form>


	<%-- 15 reloadTopics="reloadGrid" 16 formIds="filterForm" --%>
    <s:url var="remoteurl" action="searchBookJson"/>
    <sjg:grid
    	id="gridtable"
        dataType="json"
        href="%{remoteurl}"
        gridModel="gridModel"
        loadonce="true"
        reloadTopics="reloadGrid" 
        formIds="filterForm"> <%-- needed --%>
        <sjg:gridColumn name="id" index="id" title="id" sortable="false"/>
        <sjg:gridColumn name="author" index="author" title="author" sortable="false"/>
        <sjg:gridColumn name="name" index="name" title="name" sortable="false"/>
     </sjg:grid>
</div>
