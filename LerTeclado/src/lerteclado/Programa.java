package lerteclado;

import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        //Instancia a classe do Scanner para ler o teclado
        Scanner teclado = new Scanner(System.in);
        
        //Apresenta o que o usuário deve digitar
        System.out.print("Digite seu Nome Safado: ");
        //Le o que usuario digita e guarda na variavel
        String nomeDoMeliante;
        nomeDoMeliante = teclado.nextLine();
        
        System.out.print("Digite seu SobreNome Safado: ");
        String sobrenomeDoMeliante = teclado.nextLine();
        String nomeCompleto = nomeDoMeliante + " " + sobrenomeDoMeliante;
        
        System.out.print("Digite sua Idade nenem: ");
        int idade = teclado.nextInt();
        
        System.out.println("Olá, " + nomeCompleto + ", vc tem " + idade + " anusLuz");
        
    }
    
}
