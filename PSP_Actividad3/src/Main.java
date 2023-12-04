import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//crear usuario
		Usuario[] usuarios = {
				new Usuario("usuario1", "contrasena1"),
				new Usuario("usuario2", "contrasena2"),
				new Usuario("usuario3", "contrasena3")
		};
		
		//autenticar al usuario
		boolean autenticado = false;
		Scanner sc = new Scanner(System.in);
		
		for (int intento = 0; intento <3; intento++) {
			System.out.println("Introduce tu usuario: ");
			String userName = sc.nextLine();
			System.out.println("Introduce tu contraseña: ");
			String userPass = sc.nextLine();
			
			//buscar el usuario en la lista
			Usuario usuario = null;
			for (Usuario u : usuarios) {
				if (u.getUserName().equals(userName)) {
					usuario = u;
					break;
				}
			}
			
			//verificar la autenticacion
			if (usuario != null && usuario.getuserPassHashed().equals(usuario.HashPassword(userPass))) {
				autenticado = true;
				System.out.println("Bienvenido, " + usuario.getUserName() + "!");
				break;
			} else {
				System.out.println("nombre de usuario o contraseña incorrecta, prueba de nuevo.");
			}
		}
		
		if (!autenticado) {
			System.out.println("demasiados intentos fallidos. Saliendo del programa.");
			return;
		}
		
		//si el usuario esta autenticado seguimos con el programa
		Simetrico encriptador = new Simetrico();
		
		while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Encriptar frase");
            System.out.println("2. Desencriptar frase");
            System.out.println("3. Salir del programa");

            System.out.print("Seleccione una opción: ");
            String opcion = sc.nextLine();
            
            switch (opcion) {
            	case "1":
            		System.out.println("Escriba la frase a encriptar: ");
            		String frase =sc.nextLine();
            		encriptador.encriptarFrase(frase);
            		break;
            	case "2":
            		encriptador.desencriptarFrase();
            		break;
            	case "3":
            		System.out.println("Saliendo del programa");
            		sc.close();
            		System.exit(0);
            		break;
            	default:
            		System.out.println("opcion no valida, intentalo de nuevo.");
            }
            
		}
	}

}
