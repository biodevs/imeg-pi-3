<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:choose>
    <c:when test="${funcionario == null}"><c:set var="action" value="/funcionarios/novo"/></c:when>
    <c:otherwise><c:set var="action" value="/funcionarios/editar?id=${funcionario.id}"/></c:otherwise>
</c:choose>    
<c:choose>
    <c:when test="${error}"><c:set var="alert" value="alert alert-danger"/></c:when>
</c:choose>   

<form action="${action}" method="post">
    <c:if test="${funcionario != null}">
        <input type="hidden" name="id_funcionario" value="${funcionario.id}">
    </c:if>
    <fieldset class="well">
        <div id="warning" class="col-lg-12 ${alert}">
            <c:if test="${not empty mensagens}">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>            

                <p>Existem <strong>${mensagens.size()}</strong> erro(s) a ser(em) corrigidos.</p>

            </c:if>
            <div class="col-lg-12">
                <p></p>
                <ul>
                    <c:forEach items="${mensagens}" var="msg">
                        <li>${msg}</li>
                        </c:forEach>
                </ul>
            </div>

        </div>        
        <div class="col-lg-6 form-space">
            <label for="">Nome</label>
            <input class="form-control" type="text" id="nome_funcionario" name="nome_funcionario"/>
        </div>
        <div class="col-lg-6 form-space">
            <label for="">Email</label>
            <input class="form-control" type="text" id="nome_funcionario" name="nome_funcionario"/>
        </div>
        <div class="col-lg-6 form-space">
            <label for="">Senha</label>
            <input class="form-control" type="password" id="senha_funcionario" name="nome_funcionario"/>
        </div>
        <div class="col-lg-6 form-space">
            <label for="">confirmar Senha</label>
            <input class="form-control" type="password" id="confirmar_senha_funcionario" name="nome_funcionario"/>
        </div>

        <div class="col-lg-3 form-space">
            <label for="">Cargos </label>
            <select name="cargo_id" class="form-control" >
                <option value="0">Selecione um Cargo</option>
                <c:forEach items="${cargos}" var="cargo">
                    <option value="${cargo.id}">${cargo.nome}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-3 form-space">
            <label for="">Unidades </label>
            <select name="unidade_id" class="form-control" >
                <option value="0">Selecione um Unidade</option>
                <c:forEach items="${unidades}" var="unidade">
                    <option value="${unidade.id}">${unidade.nome}</option>
                </c:forEach>
            </select>
        </div>    
        <div class="col-lg-4">
            <label for="acesso_id">Permiss�o</label>
            <select name="acesso_id" class="form-control">
                <option value="0">Selecione um tipo de permiss�o</option>
                <c:forEach items="${acessos}" var="acesso">
                    <option value="${acesso.id}" <c:if test="${acesso.id == cargo.acesso.id}">selected="selected"</c:if> >${acesso.nome}</option>
                </c:forEach>
            </select>
        </div>        
        <div class="col-lg-12 form-space">
            <a href="<c:url value="/funcionarios"></c:url>" class="btn btn-default">Voltar</a>
                <button class="btn btn-default" type="submit" id="commit-funcionario">
                <c:choose>
                    <c:when test="${funcionario != null}">Alterar</c:when>
                    <c:otherwise>Salvar</c:otherwise>
                </c:choose>
            </button>
        </div>
    </fieldset>
</form>
