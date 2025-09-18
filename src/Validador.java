public class Validador {

    public static boolean validarCPF(String cpf) {
        // Nulo ou vazio
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }

        // remove espaços externos e os caracteres de máscara '.' e '-'
        String limpo = cpf.trim().replaceAll("[.\\-]", "");

        // Deve ter exatamente 11 dígitos numéricos
        if (!limpo.matches("\\d{11}")) {
            return false;
        }

        // Rejeita CPFs com todos os dígitos iguais (ex.: 00000000000)
        if (limpo.chars().distinct().count() == 1) {
            return false;
        }

        // Checa os dígitos verificadores (DV1 e DV2)
        return checarDigitos(limpo);
    }

    /**
     * Calcula DV1 e DV2 conforme algoritmo padrão e compara com os dígitos
     * 10 e 11 do cpf (string já sanitizada com 11 dígitos).
     */
    private static boolean checarDigitos(String cpf) {
        int[] d = cpf.chars().map(c -> c - '0').toArray();

        // DV1: pesos 10..2 sobre os 9 primeiros dígitos
        int s1 = 0;
        for (int i = 0; i < 9; i++) {
            s1 += d[i] * (10 - i);
        }
        int r1 = s1 % 11;
        int dv1 = (r1 < 2) ? 0 : 11 - r1;
        if (d[9] != dv1) {
            return false;
        }

        // DV2: pesos 11..2 sobre os 9 primeiros dígitos + DV1
        int s2 = 0;
        for (int i = 0; i < 10; i++) { // inclui o d[9] que já é DV1
            s2 += d[i] * (11 - i);
        }
        int r2 = s2 % 11;
        int dv2 = (r2 < 2) ? 0 : 11 - r2;
        return d[10] == dv2;
    }
}