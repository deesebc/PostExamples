<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>

<sj:head jqueryui="true" jquerytheme="redmond"/>
<script type="text/javascript">
<!--
	$.subscribe("reloadGrid", function(){
	   $("gridtable").trigger("reloadGrid");alert("DF");
	});
//-->
</script>
<div>
	<s:url var="filterUrl" action="gridView.html" />
	<s:form id="filterForm" action="%{filterUrl}">07
		<s:textfield key="global.item.list.name" name="searchField" />
		<sj:submit
            value="Search"
            button="true"
            onClickTopics="reloadGrid"
            indicator="indicator" />
	</s:form>


    <s:url var="remoteurl" action="gridView.html"/>
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
