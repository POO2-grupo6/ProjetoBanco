package client;

public class JuridicalPerson extends Client{


    private String cnpj;

    public JuridicalPerson(String name, String cnpj){
        super(name);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
