/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.imeg.servlet;

import br.senac.tads.pi3.imeg.dao.AcessoDao;
import br.senac.tads.pi3.imeg.dao.CargoDao;
import br.senac.tads.pi3.imeg.entity.Acesso;
import br.senac.tads.pi3.imeg.entity.Cargo;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author developer
 */
public class AlterarCargoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Cargo> cargos = new CargoDao().listar();
        request.setAttribute("cargos", cargos);
        ArrayList<Acesso> acessos = new AcessoDao().listar();
        request.setAttribute("acessos", acessos);
        if (request.getQueryString() != null) {
            if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
                int id = Integer.parseInt(request.getParameter("id"));
                Cargo cargo = new CargoDao().pesquisarPorId(id);
                request.setAttribute("cargo", cargo);
            }
        }
        request.getRequestDispatcher("WEB-INF/views/cargos/editar.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HttpSession session = request.getSession(true);
        String msg_success = (String) session.getAttribute("msg_success");
        if (msg_success != null) {
            session.removeAttribute("success");
            session.removeAttribute("msg_success");
        }

//        response.sendRedirect("cargos");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //inicia uma sessao
        ArrayList<String> mensagens = new ArrayList<>();
        HttpSession session = request.getSession(true);
        session.setAttribute("success", false);
        //instacio o DAO
        CargoDao cDao = new CargoDao();
        String nome = request.getParameter("nome_cargo");
        int acesso_id = Integer.parseInt(request.getParameter("acesso_id"));

        if (nome.isEmpty()) {
            request.setAttribute("error", true);
            mensagens.add("Nome não pode ser vazio.");
        }
        if (acesso_id == 0) {
            request.setAttribute("error", true);
            mensagens.add("Selecione um Tipo de Permissão.");

        }
        if (mensagens.size() > 0) {
            request.setAttribute("mensagens", mensagens);
            processRequest(request, response);
            return;
        }
        // pega o nome do cargo do formulário
        if (request.getParameter("id_cargo") != null) {
            Cargo cargo = new Cargo();
            cargo.setId(Integer.parseInt(request.getParameter("id_cargo")));
            cargo.setNome(request.getParameter("nome_cargo"));
            cargo.setStatus(Boolean.parseBoolean(request.getParameter("ativo")));
            acesso_id = Integer.parseInt(request.getParameter("acesso_id"));
            cargo.setAcesso(new AcessoDao().pesquisarPorId(acesso_id));

            if (cDao.alterar(cargo)) {
                mensagens.clear();
                session.setAttribute("success", true);
                session.setAttribute("msg_success", "Cargo <strong>" + cargo.getNome() + "</strong> alterado com sucesso.");
                response.sendRedirect("cargos");
            }
        }

    }
}