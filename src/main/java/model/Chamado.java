package model;

public class Chamado {
    private String titulo;
    private String solicitante;
    private String descricao;

    public enum Status {
        ABERTO,
        EM_ATENDIMENTO,
        RESOLVIDO,
        CANCELADO
    };

    private Status status;

    private Integer codigoEquipamento;
    private Integer salaAtendimento;
    private Departamento departamento;

    public Chamado (String titulo, String solicitante, String descricao, Integer codigoEquipamento, Integer salaAtendimento, Departamento departamento) {
        this.titulo = titulo;
        this.solicitante = solicitante;
        this.descricao = descricao;
        this.codigoEquipamento = codigoEquipamento;
        this.salaAtendimento = salaAtendimento;
        this.departamento = departamento;
        this.status = Status.ABERTO;
    }

    public static String getStatusString(Status s) {
        return switch (s) {
            case ABERTO -> "Aberto";
            case EM_ATENDIMENTO -> "Em Atendimento";
            case RESOLVIDO -> "Resolvido";
            case CANCELADO -> "Cancelado";
            default -> "Sem status";
        };
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
}
