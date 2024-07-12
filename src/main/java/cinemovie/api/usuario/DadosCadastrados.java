package cinemovie.api.usuario;

public record DadosCadastrados(

    String nome,
    String email,
    String senha,
    String confirmacaoSenha ) {
    
    public DadosCadastrados {
        System.out.println("Nome: " + nome);
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
        System.out.println("Confirmação de Senha: " + confirmacaoSenha);

        if (!senha.equals(confirmacaoSenha)) {
            throw new IllegalArgumentException("Senha e confirmação de senha devem ser iguais");
        }
    }
}
