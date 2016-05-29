<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty categorias}">
    <c:choose>
        <c:when test="${sessionScope.success}"><c:set var="mensagem" value="${msg_success}"/><c:set var="alert"  value="alert alert-success"/></c:when>
    </c:choose>
<div class="col-lg-12">
    <h3><i class="fa fa-archive" aria-hidden="true"></i>&nbsp;Categorias</h3>
    <a href="<c:url value="/categorias/novo"></c:url>"><i class="fa fa-newspaper-o" aria-hidden="true"></i>&nbsp;Nova Categoria</a>
    <br>
    <div id="warning" class="col-lg-12 ${alert}">
        <c:if test="${sessionScope.success}">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </c:if>
        ${mensagem}
    </div>
</div>    
<div class="col-lg-12 table-reposnsive">
    <table class="table table-hover">
        <thead>
            <th>Status</th>
            <th>Nome</th>
            <th>A��es</th>
        </thead>
        <tbody>
        <c:forEach items="${categorias}" var="categoria">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${categoria.isStatus()}">
                            <em data-toggle="tooltip" data-placement="top" title="Ativado" class="active-elem-table glyphicon glyphicon glyphicon-ok-circle"></em>
                        </c:when>
                        <c:otherwise>
                            <em data-toggle="tooltip" data-placement="top" title="Desativado" class="active-elem-table glyphicon glyphicon-remove-circle color-elem-table-deactive"></em> 
                        </c:otherwise></c:choose>
                </td>
                <td>${categoria.getNome()}</td>
                <td><center><a href="<c:url value="/categorias/editar?id=${categoria.getId()}"></c:url>"><i data-toggle="tooltip" data-placement="top" title="Editar" class="active-elem-table fa fa-pencil-square-o" aria-hidden="true"></i></a></center></td>                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</c:if>