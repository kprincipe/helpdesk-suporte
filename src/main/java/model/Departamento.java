package model;

public class Departamento {
    private String codigo;
    private String nome;
    private String sigla;
    private  String responsavel;

    public Departamento(String codigo, String nome, String sigla, String responsavel) {
        this.codigo = codigo;
        this.nome = nome;
        this.sigla = sigla;
        this.responsavel = responsavel;
    }

    public String getCodigo() {
        return this.codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return this.sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getResponsavel() {
        return this.responsavel;
    }
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

}
