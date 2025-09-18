public class Validador {

    public static boolean validarCPF(String cpf) {
        // 1. Nulo ou vazio
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        // 2. Sanitiza: remove espaços e caracteres da máscara
        String limpo = cpf.trim().replaceAll("[.-]", "");

        // 3. Verifica se tem exatamente 11 dígitos
        if (!limpo.matches("\\d{11}")) {
            return false;
        }

        // 4. Rejeita sequências repetidas (ex.: "00000000000")
        if (limpo.chars().distinct().count() == 1) {
            return false;
        }

        // 👉 Ainda não estamos validando DV (isso virá depois)
        return true;
    }
}
