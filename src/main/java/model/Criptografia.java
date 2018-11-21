package model;

public class Criptografia {

    private StringBuilder letras = null;
    private static final int limiteZ = (int) 'z';
    private static final int limiteA = (int) 'a';
    private static final int limite9 = (int) '9';
    private static final int limite0 = (int) '0';

    public String criptografar(String dado) {
        /* Criptografa o dado usando a cifra de César*/
        if (dado.isEmpty()) return null;
        letras = new StringBuilder();
        for (int i = 0; i < dado.length(); i++) {
            int letraASC = ((int) dado.charAt(i)) + 3;
            while (letraASC > limiteZ)
                letraASC -= 26;
            while (letraASC > limite9 && letraASC < limiteA)
                letraASC -= 10;
            letras.append((char) letraASC);
        }
        return letras.toString();

    }

    public String descriptografar(String dado) {
        if (dado.isEmpty() || dado == null) return null;
        letras = new StringBuilder();
        for(int i = 0; i < dado.length(); i++){
            int letraASC = ((int) dado.charAt(i) - 3);
            while(letraASC < limiteA && letraASC > limite9) 
                letraASC += 26;
            while(letraASC < limite0)
                letraASC += 10;
            letras.append((char) letraASC);
        }
        return letras.toString();
    }

}