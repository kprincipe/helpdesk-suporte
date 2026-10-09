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

        saida.println("oi mundo");

        for (Chamado chamado : chamados) {
            
        }
    }
}
