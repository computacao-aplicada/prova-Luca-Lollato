public class Validador {

    public static boolean validarCPF(String cpf) {
        // Nulo ou vazio
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        // Sanitiza: tira espaços e caracteres da máscara
        String limpo = cpf.trim().replaceAll("[.-]", "");

        // Verifica se são exatamente 11 dígitos
        if (!limpo.matches("\\d{11}")) {
            return false;
        }

        // Rejeita sequências repetidas (00000000000, 11111111111, etc.)
        if (limpo.chars().distinct().count() == 1) {
            return false;
        }

        // 👉 Para o Passo 1: consideramos válido até aqui
        return true;
    }
}
