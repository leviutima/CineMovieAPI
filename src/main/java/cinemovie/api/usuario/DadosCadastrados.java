package cinemovie.api.usuario;

public record DadosCadastrados(

    String nome,
    String email,
    String senha ) {
    
    public DadosCadastrados {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);

    }


}
