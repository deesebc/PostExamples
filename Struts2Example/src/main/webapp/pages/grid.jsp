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
	<s:form id="filterForm" action="gridView2">11
		<s:textfield key="global.item.list.name" name="author" />
		<s:textfield key="global.item.list.name" name="name" />
		<sj:submit
            value="Search2"
            button="true"
            onClickTopics="reloadGrid"
            indicator="indicator" />
	</s:form>


    <s:url var="remoteurl" action="gridView"/>
    <sjg:grid
    	id="gridtable"
        dataType="json"
        href="%{remoteurl}"
        gridModel="gridModel"
        loadonce="true"
        reloadTopics="reloadGrid"
        formIds="filterForm">
        <sjg:gridColumn name="name" index="name" title="Student Name" sortable="false"/>
        <sjg:gridColumn name="age" index="age" title="Age" sortable="false"/>
        <sjg:gridColumn name="email" index="mail" title="Email" sortable="false"/>
        <sjg:gridColumn name="address" index="address" title="Address" sortable="false"/>
        <sjg:gridColumn name="contact" index="contact" title="Contact" sortable="false"/>
     </sjg:grid>
</div>
