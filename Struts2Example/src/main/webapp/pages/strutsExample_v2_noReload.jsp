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
	<s:form id="filterForm" action="%{filterUrl}">
		<s:textfield key="global.item.list.name" name="searchField" />
		<sj:a targets="gridtable" button="true" key="global.item.list.form.submit" formIds="filterForm" onClickTopics="reloadMyGrid" />
		<sj:submit onClickTopics="reloadMyGrid" button="true">02</sj:submit>
	</s:form>


    <s:url var="remoteurl" action="gridView.html"/>
    <sjg:grid
            id="gridtable"
            caption="Student Data"
            dataType="json"
            href="%{remoteurl}"
            pager="true"
            gridModel="gridModel"
            rowList="10,15,20"
            rowNum="15"
           reloadTopics="reloadMyGrid"
            rownumbers="true"
            autowidth="false"
            >
        <sjg:gridColumn name="name" index="name" title="Student Name" sortable="false"/>
        <sjg:gridColumn name="age" index="age" title="Age" sortable="false"/>
        <sjg:gridColumn name="email" index="mail" title="Email" sortable="false"/>
        <sjg:gridColumn name="address" index="address" title="Address" sortable="false"/>
        <sjg:gridColumn name="contact" index="contact" title="Contact" sortable="false"/>
     </sjg:grid>
</div>
