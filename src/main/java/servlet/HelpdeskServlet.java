package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Chamado;
import model.Departamento;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class HelpdeskServlet extends HttpServlet {
    private ArrayList<Departamento> departamentos;
    private ArrayList<Chamado> chamados;

    public void init() {
        this.departamentos = new ArrayList<Departamento>();
        this.chamados = new ArrayList<Chamado>();

        Departamento DAIC = new Departamento(
                "DAIC7SET",
                "Departamento Acadêmico de Informação e Comunicação",
                "DAIC",
                "Prof. Me. Everton Moura Arruda");

        Chamado chamadoDAIC = new Chamado(
                "Problema no lançamento de notas do SIGAA",
                "Emmerson Santa Rita",
                "Não consigo lançar notas, pois o SIGAA está fora do ar",
                10001,
                2,
                DAIC);

        Departamento CAPNE = new Departamento(
                "CAPNEDUQ",
                "Coordenação de Atendimento às Pessoas com Necessidades Específicas",
                "CAPNE",
                "Roberta Enir");

        Chamado chamadoCAPNE = new Chamado(
                "Monitor quebrado",
                "Roberta Enier",
                "Monitor queimou por causa de uma goteira",
                12003,
                1,
                CAPNE);

        Departamento BPPS = new Departamento(
                "BIBLIOCDI",
                "Biblioteca Professor Paulo Sarmento",
                "BPPS",
                "Luna Principe");

        Chamado chamadoBBPS = new Chamado(
                "WI-FI instável",
                "Dona Maria",
                "O WI-FI fica lento no almoço",
                13025,
                1,
                BPPS);

        departamentos.add(DAIC);
        departamentos.add(CAPNE);
        departamentos.add(BPPS);

        chamados.add(chamadoDAIC);
        chamados.add(chamadoCAPNE);
        chamados.add(chamadoBBPS);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter saida = res.getWriter();

        String acao = req.getParameter("acao");
        String pesquisa = req.getParameter("pesquisa");

        if (acao == null) {
            acao = "listar";
        }

        if (pesquisa == null) {
            pesquisa = "";
        }

        if (acao.equals("excluir")) {
            String idExcluir = req.getParameter("id");

            if (idExcluir != null && !idExcluir.isEmpty()) {

                int indice = Integer.parseInt(idExcluir);

                if (indice >= 0 && indice < chamados.size()) {
                    chamados.remove(indice);
                }
            }
            res.sendRedirect("helpdesk");
        }

        String botaoSalvar = req.getParameter("salvar");
        if (botaoSalvar != null) {
            String parId = req.getParameter("id");

            String titulo = req.getParameter("titulo");
            String solicitante = req.getParameter("solicitante");
            String descricao = req.getParameter("descricao");
            String codigoEquip = req.getParameter("codigoequip");
            String salaAtendimento = req.getParameter("sala");
            String departamento = req.getParameter("departamento");
            String statusId = req.getParameter("status");

            Departamento departamentoSelecionado = departamentos.get(Integer.parseInt(departamento));

            if (parId == null || parId.equals("")) {
                Chamado novoChamado = new Chamado(titulo, solicitante, descricao, Integer.parseInt(codigoEquip), Integer.parseInt(salaAtendimento), departamentoSelecionado);

                chamados.add(novoChamado);

            } else {
                int indice = Integer.parseInt(parId);
                Chamado chamadoAlteraco = chamados.get(indice);

                chamadoAlteraco.setTitulo(titulo);
                chamadoAlteraco.setSolicitante(solicitante);
                chamadoAlteraco.setDescricao(descricao);
                chamadoAlteraco.setCodigoEquipamento(Integer.parseInt(codigoEquip));
                chamadoAlteraco.setSalaAtendimento(Integer.parseInt(salaAtendimento));
                chamadoAlteraco.setDepartamento(departamentoSelecionado);

                if (statusId != null) {
                    chamadoAlteraco.setStatus(Chamado.Status.values()[Integer.parseInt(statusId)]);
                }
            }
            res.sendRedirect("helpdesk");
        }

        res.setContentType("text/html;charset=UTF-8");

        // Cabeçalho
        saida.println("<!DOCTYPE html>");
        saida.println("<html lang=\"pt-br\">");

        saida.println("<head>");
        saida.println("    <meta charset=\"UTF-8\">");
        saida.println("    <title>Helpdesk Suporte</title>");
        saida.println("    <style>html *\n" +
                "{\n" +
                "   font-size: 0.9em !important;\n" +
                "   color: #000 !important;\n" +
                "   font-family: \"Comic Sans MS\", sans-serif;\n" +
                "}</style>");
        saida.println("</head>");

        saida.println("<body>");
        saida.println("<h1>Helpdesk - Chamados</h1>");

        // Definição de ações
        if (acao.equals("listar")) {
            saida.println("<a href=\"helpdesk?acao=criar\">+ Criar Chamado</a><br><br>");

            saida.println("    <form method='GET' action='helpdesk'>");
            saida.println("        <label type='text' id='filtroPesquisar' name='pesquisa' value='' >Buscar</label>");
            saida.println("        <input type='text' id='filtroPesquisar' name='pesquisa' value='' placeholder='Pinco Palino'></input>");
            saida.println("        <button type=\"submit\">Pesquisar</button>");
            saida.println("    </form>");

            saida.println("    <table border=\"1px\" style='    text-align: center; border: 2px inset; border-collapse: collapse;'>");
            saida.println("        <caption>Lista de chamados</caption>");
            saida.println("        <thead>");
            saida.println("            <tr>");
            saida.println("                <th>Título</th>");
            saida.println("                <th>Solicitante</th>");
            saida.println("                <th>Descrição</th>");
            saida.println("                <th>Código do Equipamento</th>");
            saida.println("                <th>Sala</th>");
            saida.println("                <th>Departamento</th>");
            saida.println("                <th>Ação</th>");
            saida.println("                <th>Status</th>");
            saida.println("            </tr>");
            saida.println("            <tbody>");

            for (int i = 0; i < chamados.size(); i++) {
                Chamado chamadoLista = chamados.get(i);

                if (!chamadoLista.getTitulo().contains(pesquisa)) continue;

                saida.println("                <tr>");
                saida.println("                    <td>" + chamadoLista.getTitulo() + "</td>");
                saida.println("                    <td>" + chamadoLista.getSolicitante() + "</td>");
                saida.println("                    <td>" + chamadoLista.getDescricao() + "</td>");
                saida.println("                    <td>" + chamadoLista.getCodigoEquipamento().toString() + "</td>");
                saida.println("                    <td>" + chamadoLista.getSalaAtendimento().toString() + "</td>");
                saida.println("                    <td>" + chamadoLista.getDepartamento().getSigla() + "</td>");
                saida.println("                    <td>" + Chamado.getStatusString(chamadoLista.getStatus())+ "</td>");
                saida.println("                    <td>");
                saida.println("                        <a href=\"helpdesk?acao=alterar&id=" + i + "\">Alterar</a>");
                saida.println("                        <a href=\"helpdesk?acao=excluir&id=" + i + "\" onclick=\"return confirm(\"Deseja realmente excluir?\");\">Excluir</a>");
                saida.println("                    </td>");
                saida.println("                </tr>");
            }

            saida.println("            </tbody>");
            saida.println("        </thead>");
            saida.println("    </table>");
        } else if (acao.equals("criar") || acao.equals("alterar")) {
            String titulo = "Criar Chamado";
            Chamado chamadoEdicao = null;
            String idAlterar = null;

            if (acao.equals("alterar")) {
                idAlterar = req.getParameter("id");

                int indice = Integer.parseInt(idAlterar);

                chamadoEdicao = chamados.get(indice);

                titulo = "Editar Chamado";
            }

            saida.println("    <h2>" + titulo +"</h2>");

            saida.println("    <form method='GET' action='helpdesk'>");

            saida.println("        <input type='hidden' id='id' name='id' value='" +
                    (chamadoEdicao != null ? idAlterar : "") + "'></input>");

            saida.println("        <label type='text' for='campoTitulo'>Titulo: </label><br>");
            saida.println("        <input type='text' id='campoTitulo' name='titulo' value='" + (chamadoEdicao != null ? chamadoEdicao.getTitulo() : "") + "' placeholder='Titulo' required></input>");
            saida.println("        <br>");

            saida.println("        <label type='text' for='campoSolicitante'>Solicitante: </label><br>");
            saida.println("        <input type='text' id='campoSolicitante' name='solicitante' value='" + (chamadoEdicao != null ? chamadoEdicao.getSolicitante() : "") + "' placeholder='Nome' required></input>");
            saida.println("        <br>");

            saida.println("        <label type='text' for='campoDescricao'>Descricao: </label><br>");
            saida.println("        <textarea id='campoDescricao' name='descricao'  placeholder='Descrição' required>" + (chamadoEdicao != null ? chamadoEdicao.getDescricao() : "") + "</textarea>");
            saida.println("        <br>");

            saida.println("        <label type='text' for='campoCodigoEquip'>Código do Equipamento: </label><br>");
            saida.println("        <input type='text' id='campoCodigoEquip' name='codigoequip' value='" + (chamadoEdicao != null ? chamadoEdicao.getCodigoEquipamento() : "") + "' placeholder='10021' required></input>");
            saida.println("        <br>");

            saida.println("        <label type='text' for='campoSala'>Sala: </label><br>");
            saida.println("        <input type='text' id='campoSala' name='sala' value='" + (chamadoEdicao != null ? chamadoEdicao.getSalaAtendimento() : "") + "' placeholder='2' required></input>");
            saida.println("        <br>");

            if (acao.equals("alterar")) {
                saida.println("        <label type='text' for='campoStatus'>Status: </label><br>");
                saida.println("        <select id='campoStatus' name='status' value='" + (chamadoEdicao != null ? chamadoEdicao.getStatus() : "") + "' required>");

                int c = 0;
                for (Chamado.Status s: Chamado.Status.values()) {
                    boolean selecionada = (chamadoEdicao != null) && (chamadoEdicao.getStatus() == s);

                    saida.println("        <option value='" + c + "' " + (selecionada ? "selected" : "") + ">" + Chamado.getStatusString(s) + "</option>");
                    c++;
                }

                saida.println("        </select>");
                saida.println("        <br>");
            }

            saida.println("        <label type='text' for='campoDepartamento'>Departamento: </label><br>");
            saida.println("        <select id='campoDepartamento' name='departamento' value='" + (chamadoEdicao != null ? chamadoEdicao.getDepartamento().getSigla() : "") + "' required>");

            for (int i = 0; i < departamentos.size(); ++i) {
                boolean selecionada = selecionada = (chamadoEdicao != null) && (chamadoEdicao.getDepartamento() != null);

                saida.println("        <option value='" + i + "' " + (selecionada ? "selected" : "") + ">" + departamentos.get(i).getSigla() + "</option>");
            }

            saida.println("        </select>");

            saida.println("        <br>");
            saida.println("        <button type='submit' name='salvar' value='pressionado'>Salvar</button>");
            saida.println("        <a href='helpdesk?acao=listar'>Cancelar<a/>");
            saida.println("    </form>");
        }

        saida.println("</body>");
        saida.println("</html>");
    }
}
