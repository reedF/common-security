<#-- 
 * 分页宏 
 * author: reed 
 -->
<#macro pager __url __page __page_total> 

	<#assign __PAGE_HEAD_LEN = 5/>

    <#assign __PAGE_TAIL_LEN = (__page_total?number - __PAGE_HEAD_LEN + 1) /> 

    <#assign __PAGE_OFFSET = 2/> 
      
    <#assign __PAGE_HEAD_FLAG = __PAGE_HEAD_LEN - __PAGE_OFFSET />
    <#assign __PAGE_TAIL_FLAG = __PAGE_TAIL_LEN + __PAGE_OFFSET />

    <#-- $__page <= 5 -->
    <#if (__page?number lte __PAGE_HEAD_LEN)>
        <#if (__page?number == 1) >
            <a href="javascript:void(0)" disabled="true">上一页</a> 
        <#else>
            <#assign __pre_page = __page?number - 1 /> 
            <a href="${__url}${__pre_page}">上一页</a> 
        </#if>  
        <#if (__page?number lte __PAGE_HEAD_FLAG)>
            <#list 1..__PAGE_HEAD_LEN as __p>
                <#if __p == __page?number>
                    <a href="javascript:void(0);" disabled="true">${__p}</a> 
                <#elseif __p lt __page_total?number> 
                    <a href="${__url}${__p}" >${__p}</a> 
                </#if>
            </#list>
        <#else>
			<#if (__page?number + __PAGE_OFFSET) lte __page_total?number >
				<#assign __len = __page?number + __PAGE_OFFSET/>
			<#else>
				<#assign __len = __page_total?number />
			</#if>
            <#list 1..__len as __p>
                <#if (__p == __page?number)> 
                    <a href="javascript:void(0);" disabled="true">${__p}</a> 
                <#elseif __p lt __page_total?number> 
                    <a href="${__url}${__p}" >${__p}</a> 
                </#if>
            </#list>
        </#if>
        <span>...</span> 
        <a href="${__url}${__page_total}">${__page_total}</a> 
        <#assign __next_page = __page?number + 1 /> 
		<#if __next_page lte __page_total?number >
        <a href="${__url}${__next_page}">下一页</a> 
		</#if>
    <#elseif (__page?number gte __PAGE_TAIL_LEN)>
       <#assign __pre_page = __page?number - 1 /> 
            <a href="${__url}${__pre_page}">上一页</a> 
       <#if __page?number gte __PAGE_TAIL_FLAG>
            <#list __PAGE_TAIL_FLAG..__page_total?number as __p> 
                <#if __p == __page?number> 
                    <a href="javascript:void(0);" disabled="true">${__p}</a> 
                <#else> 
                    <a href="${__url}${__p}" >${__p}</a> 
                </#if> 
            </#list> 
       <#else>
            <#assign __len = (__page?number - __PAGE_OFFSET)/>
			<#if __len gt 0 >
				<#list __len..__page_total?number as __p> 
					<#if __p == __page?number > 
						<a href="javascript:void(0);" disabled="true">${__p}</a> 
					<#elseif __p lt __page_total?number> 
						<a href="${__url}${__p}" >${__p}</a> 
					</#if> 
				</#list> 
			</#if>
        </#if>
        <#if __page?number == __page_total?number>
            <a href="javascript:void(0);" disabled="true">下一页</a> 
        <#else> 
            <#assign __pre_page = (__page?number + 1) />
            <a href="${__url}${__pre_page}">上一页</a> 
        </#if> 
    <#else> 
       <#assign __pre_page = (__page?number - 1) />
        <a href=""${__url}${__pre_page}">上一页</a>  
        <a href=""${__url}1">1</a> 
        <span>...</span> 
        <#assign __start = (__page?number - __PAGE_OFFSET) /> 
        <#assign __end = (__page?number + __PAGE_OFFSET) />
        <#list __start..__end as __p > 
            <#if __p == __page?number > 
                <a href="javascript:void(0);" disabled="true" >${__p}</a> 
            <#elseif __p lt __page_total?number> 
                <a href="${__url}${__p}" >${__p}</a> 
            </#if> 
        </#list> 
        <span>...</span> 
        <a href="${__url}${__page_total}">${__page_total}</a> 
        <#assign __pre_page = (__page?number + 1) />
        <a href="${__url}${__pre_page}">上一页</a> 
    </#if> 
</#macro>