package model;

public class Chamado {
    private String titulo;
    private String solicitante;
    private String descricao;
    private Integer codigoEquipamento;
    private Integer salaAtendimento;
    private Departamento departamento;

    // Adição do atributo prioridade
    private String prioridade;

    public Chamado (String titulo, String solicitante, String descricao, Integer codigoEquipamento, Integer salaAtendimento, Departamento departamento, String prioridade) {
        this.titulo = titulo;
        this.solicitante = solicitante;
        this.descricao = descricao;
        this.codigoEquipamento = codigoEquipamento;
        this.salaAtendimento = salaAtendimento;
        this.departamento = departamento;
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCodigoEquipamento() {
        return codigoEquipamento;
    }

    public void setCodigoEquipamento(Integer codigoEquipamento) {
        this.codigoEquipamento = codigoEquipamento;
    }

    public Integer getSalaAtendimento() {
        return salaAtendimento;
    }

    public void setSalaAtendimento(Integer salaAtendimento) {
        this.salaAtendimento = salaAtendimento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getPrioridade() { return prioridade; }

    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
}
