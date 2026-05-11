<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@taglib prefix="c" uri="jakarta.tags.core" %>
    
    <c:set var="startnum" value="${searchdto.pagination.startPage}"/>
    <c:set var="endnum" value="${searchdto.pagination.endPage}"/>
    <c:set var="totalpagecount" value="${searchdto.pagination.totalPageCount}"/>
    
    <div id="paging">
    <table >
    	<tr>
    		<c:if test="${startnum gt 1}">
    		<td><a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=1"> 처음 </a></td>
    	    <td><a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${startnum-1}"> 이전 </a></td>
    		</c:if>
    		
    		<c:forEach var="pagenum" begin="${startnum}" end="${endnum}" step="1">
    			<td>
    				<a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${pagenum}">
    				${pagenum}
    				</a>
    			</td>
    		</c:forEach>
    		
    		<c:if test="${endnum lt totalpagecount}">
    		<td><a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=1"> 다음 </a></td>
    		<td><a href="/BoardPaging/List?menu_id=${menu_id}&nowpage=${totalpagecount+1}"> 마지막 </a></td>
    		</c:if>
    		
    	</tr>
    </table>
    </div>