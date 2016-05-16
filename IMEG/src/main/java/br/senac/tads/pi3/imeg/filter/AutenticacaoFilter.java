/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.imeg.filter;

import br.senac.tads.pi3.imeg.dao.AcessoDao;
import br.senac.tads.pi3.imeg.entity.Funcionario;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Márcio Soares <marcio@mail.com>
 */
@WebFilter(filterName = "AutenticacaoFilter", servletNames = {"AlterarCargoServlet",
    "AlterarCategoriaServlet",
    "AlterarFuncionarioServlet",
    "AlterarProdutoServlet",
    "AlterarUnidadeServlet",
    "CargosServlet",
    "CategoriasServlet",
    "FuncionariosServlet",
    "InserirProdutoServlet",
    "NovaCategoriaServlet",
    "NovaUnidadeServlet",
    "NovoCargoServlet",
    "NovoFuncionarioServlet",
    "NovoProdutoServlet",
    "ProdutosServlet",
    "UnidadesServlet"
})
public class AutenticacaoFilter implements Filter {

    private static List paginas;
    private static List permissao;

    public AutenticacaoFilter() {
        AutenticacaoFilter.paginas = Arrays.asList(
                "AlterarCargoServlet",
                "AlterarCategoriaServlet",
                "AlterarFuncionarioServlet",
                "AlterarProdutoServlet",
                "AlterarUnidadeServlet",
                "CargosServlet",
                "CategoriasServlet",
                "FuncionariosServlet",
                "InserirProdutoServlet",
                "NovaCategoriaServlet",
                "NovaUnidadeServlet",
                "NovoCargoServlet",
                "NovoFuncionarioServlet",
                "NovoProdutoServlet",
                "ProdutosServlet",
                "UnidadesServlet");
        AutenticacaoFilter.permissao = Arrays.asList(new AcessoDao().listarPorNome());
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession sessao = httpRequest.getSession();
//        Funcionario funcionario = (Funcionario) sessao.getAttribute("funcionario");
//        if (funcionario == null) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
//            return;
//        }
//        try {
//            if (verificarAcesso(funcionario, httpRequest, httpResponse)) {
//                chain.doFilter(request, response);
//            } else {
//                httpResponse.sendRedirect("erroNaoAutorizado.jsp");
//            }
//        } catch (IOException | ServletException t) {
//            t.printStackTrace();
//        }
    }

    /**
     * Método que verifica se o usuário possui o(s) papel(is) necessário(s) para
     * acessar a funcionalidade
     *
     * @param usuario
     * @param req
     * @param resp
     * @return
     */
    private static boolean verificarAcesso(Funcionario funcionario, HttpServletRequest req, HttpServletResponse resp) {
        return paginas.contains(req.getRequestURI()) && permissao.contains(funcionario.getAcesso().getNome());
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

}