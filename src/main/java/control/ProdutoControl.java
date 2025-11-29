/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import dao.ProdutoDAO;
import model.ProdutoModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author humbe
 */
@WebServlet("/produto/*")
public class ProdutoControl extends HttpServlet {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private Gson gson = new Gson();

    private void setCors(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    // GET -> retorna todos os produtos
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCors(resp);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            List<ProdutoModel> produtos = produtoDAO.findAll();
            out.print(gson.toJson(produtos));
        } catch (Exception e) {
            resp.setStatus(500);
            out.print("{\"erro\":\"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCors(resp);
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    // POST -> cria novo produto
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    BufferedReader reader = req.getReader();
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        sb.append(line);
    }

    System.out.println("JSON recebido: " + sb.toString());

    setCors(resp);
    resp.setContentType("application/json");
    PrintWriter out = resp.getWriter();

    try {
        ProdutoModel produto = gson.fromJson(sb.toString(), ProdutoModel.class);
        produtoDAO.insert(produto);

        resp.setStatus(201);
        out.print("{\"mensagem\":\"Produto criado com sucesso\"}");
    } catch (Exception e) {
        resp.setStatus(500);
        out.print("{\"erro\":\"" + e.getMessage() + "\"}");
    }
}

    // PUT -> atualiza produto existente
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCors(resp);
        
    resp.setContentType("application/json");
    PrintWriter out = resp.getWriter();

    try {
        // pega /produto/5 → extrai o 5
        String path = req.getPathInfo(); // "/5"
        long idProduto = Long.parseLong(path.substring(1));

        ProdutoModel produto = gson.fromJson(req.getReader(), ProdutoModel.class);
        produto.setIdProduto(idProduto);

        produtoDAO.update(produto);
        out.print("{\"mensagem\":\"Produto atualizado com sucesso\"}");
    } catch (Exception e) {
        resp.setStatus(500);
        out.print("{\"erro\":\"" + e.getMessage() + "\"}");
    }
    }

    // DELETE -> deleta produto
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCors(resp);
    resp.setContentType("application/json");
    PrintWriter out = resp.getWriter();

    try {
        // pega /produto/5 → extrai o 5
        String path = req.getPathInfo(); // "/5"

        if (path == null || path.equals("/")) {
            resp.setStatus(400);
            out.print("{\"erro\":\"ID não enviado na URL\"}");
            return;
        }

        long idProduto = Long.parseLong(path.substring(1)); // remove a barra

        produtoDAO.delete(idProduto);
        out.print("{\"mensagem\":\"Produto deletado\"}");
    } catch (Exception e) {
        resp.setStatus(500);
        out.print("{\"erro\":\"" + e.getMessage() + "\"}");
    }
}
}
