/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author gdragoni
 */
public class Tabuleiro {
    
    public static Integer rodarRoleta() {
        double value = Math.random()*10+1;
        int intValue = (int)value;
        return intValue;
    }
    
    public static AcaoTabuleiro acaoParaCasa(Integer nCasa) {
        switch(nCasa) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return AcaoTabuleiro.SALARIO;
            case 11:
            case 13:
            case 16:
            case 21:
            case 24:
            case 26:
            case 33:
            case 34:
            case 37:
            case 39:
            case 41:
            case 43:
            case 45:
            case 47:
            case 51:
            case 52:
            case 56:
            case 57:
            case 62:
            case 64:
            case 69:
                return AcaoTabuleiro.GANHE_DINHEIRO;
            case 12:
            case 15:
            case 17:
            case 23:
            case 25:
            case 27:
            case 36:
            case 38:
            case 46:
            case 54:
            case 55:
            case 58:
            case 63:
            case 65:
            case 68:
                return AcaoTabuleiro.PERDE_DINHEIRO;
            case 20:
            case 30:
            case 40:
            case 50:
            case 60:
                return AcaoTabuleiro.DIA_DE_PAGAMENTO;  
            case 14:
            case 19:
            case 22:
            case 29:
            case 32:
                return AcaoTabuleiro.CASAMENTO;
            case 49:
            case 61:
            case 66:
                return AcaoTabuleiro.DIVORCIO;
            case 35:
            case 31:
            case 42:
            case 48:
            case 59:
                return AcaoTabuleiro.FILHO;
            case 18:
            case 28:
            case 44:
            case 53:
            case 67:
                return AcaoTabuleiro.VINGANCA;
            case 70:
                return AcaoTabuleiro.DIA_DO_JUIZO;
            default:
                return AcaoTabuleiro.NADA;
        }
    }
    
    public enum AcaoTabuleiro {
        SALARIO,
        DIA_DE_PAGAMENTO,
        GANHE_DINHEIRO,
        PERDE_DINHEIRO,
        VINGANCA,
        FILHO,
        CASAMENTO,
        DIVORCIO,
        DIA_DO_JUIZO,
        NADA,
    }
}
