
import java.util.Scanner;
public class Banca_Investimenti {
	
	static double soldiBanca = 0;
	static double portafoglio = 0;
	static int mesi = 0;
	
	public static void menuPrincipale () {
		System.out.println("-----MENU-----");
		System.out.println("1. DEPOSITARE");
		System.out.println("2. PRELEVARE");
		System.out.println("3. INVESTIRE");
		System.out.println("4. Visualizzare lo stato del conto in banca");
		System.out.println("5. Visualizzare lo stato del portafoglio");
		System.out.println("6. Mese successivo\n");
		System.out.println("7. Informazionni sull'investimento");
		
		System.out.println("0. Esci.");
	}
	
	public static void menuDurataInvestimento () {
		System.out.println("-----MENU DURATA DELL'INVESTIMENTO-----");
		System.out.println("1. BREVE");
		System.out.println("2. MEDIA");
		System.out.println("3. LUNGA");
	}
	
	public static void menuRiskLevel () {
		System.out.println("-----MARGINI DI RISCHIO E GUADAGNO-----");
		System.out.println("1. BASSO");
		System.out.println("2. MEDIO");
		System.out.println("3. ALTO");
	}
	
	public static boolean depositare (double depositMoney) {
		
		if ((depositMoney <= portafoglio) && (depositMoney > 0)) {
			portafoglio -= depositMoney;
			soldiBanca += depositMoney;
			return true;
		}
		
		return false;
	}
	
	public static boolean prelevare (double withdrawals) {
		
		if ((withdrawals <= soldiBanca) && (withdrawals > 0)) {
			soldiBanca -= withdrawals;
			portafoglio += withdrawals;
			return true;
		}
		
		return false;
	}
	
	/*public static double investire (double capitale, int proEarn, int proSame, int proLose, 
									int percEarn, int percHighRisk, int percLoss, int anni) {
		
		/*I percentuali e le probabilita' devono essere int o double (puo' essere che dico 
		"Ogni anno c'e' la probabilita' di 33,5% di guadagnare 2,5%")?
		
		double probabilitaEarn = (double)proEarn/100;
		double probabilitaSame = (double)proSame/100;
		double probabilitaLose = (double)proLose/100;
		double percentualeEarn = (double)percEarn/100;
		double percentualeLoss = (double)percLoss/100;
		double percentualeHighRisk = (double)percHighRisk/100;
		
		for (int i=0; i<anni; i++) {
			double probabilita = Math.random();
			if (probabilita <= probabilitaEarn) {
				capitale = capitale * (1 + percentualeEarn);
			} else {
				if (probabilita > (probabilitaEarn + probabilitaSame + probabilitaLose)) {
					capitale = capitale * (1 - percentualeHighRisk);
				} else {
					capitale = capitale * (1 - percentualeLoss);
				}
			}
		}
		
		return capitale;
	}*/
	
	public static double investimento (double capitale, int probabilitaGuadagno, int percentuali[]) {

		int probabilita = (int)Math.random() % 100 + 1;
		
		if (probabilita <= probabilitaGuadagno) {
			int percentualeGuadagno = (int)Math.random() % (percentuali[0] - 1)+1+1;
			capitale = capitale * (1 + percentualeGuadagno);
		} else {
			int percentualePerdita = (int)Math.random() % (percentuali[1] - 1)+1+1;
			capitale = capitale * (1 - percentualePerdita);
		}

		return capitale;
	}
	
	public static void informazioniInvestimento () {
		
	}
	
	public static void main (String[] args) {
		Scanner tastiera = new Scanner (System.in);
		char scelta;
		int anniToMesi = 0;
		
		do {
			do {
				if (mesi == 0) {
					System.out.println ("- - - Benvenuti alla nostra banca - - -");
					System.out.println ("Non hai soldi nel tuo portafoglio.");
					System.out.println ("Desideri visualizzaeo lo stato del tuo account? o venire il mese successivo?");
				}
				menuPrincipale();
				System.out.println ("Cosa vuoi fare?");
				scelta = tastiera.next().charAt(0);
			} while (scelta < '0' || scelta > '7'); 
			
			switch (scelta) {
				case '1':{
					
					double depositMoney = -1.0;
					boolean ok = true;
					
					do {
						System.out.println ("Quanti soldi desideri depositare?");
						tastiera.nextLine();
						String stringa = tastiera.nextLine();
						stringa.trim();
						try {
							depositMoney = Double.parseDouble(stringa);
						} catch (NumberFormatException e) {
							System.out.println ("Formato non e' double.");
							ok = false;
						}
					} while (!ok);
					
					if (!depositare(depositMoney)) {
						System.out.println ("Soldi non sufficiente nel portafoglio.");
						System.out.println ("Scegliere una cifra minore o aspettare per il mese successivo.");
					} else {
						System.out.println ("Il deposito e' andato a buon fine.");
					}
					
					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '2':{
					
					double withdrawals = -1;
					boolean ok = true;
					
					do {
						System.out.println ("Quanti soldi desideri prelevare?");
						tastiera.nextLine();
						String stringa = tastiera.nextLine();
						stringa.trim();
						try {
							withdrawals = Double.parseDouble(stringa);
						} catch (NumberFormatException e) {
							System.out.println ("Formato non e' double.");
							ok = false;
						}
					} while (!ok);
					
					if (!prelevare(withdrawals)) {
						System.out.println ("Non hai soldi sufficiente in banca.");
						System.out.println ("Prova a diminuire la somma o depositare soldi.");
					} else {
						System.out.println ("Il deposito e' andato a buon fine.");
					}
					
					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '3':{
					
					char durata;
					double capitale = 0;
					boolean ok;
					//char rischio;
					
					do {
						ok = true;
						System.out.println ("Somma da investire: ");
						tastiera.nextLine();
						String stringa = tastiera.nextLine();
						stringa.trim();
						try {
							capitale = Double.parseDouble(stringa);
						} catch (NumberFormatException e) {
							System.out.println ("Formato non corretto.");
							ok = false;
						}
						if (ok) {
							if (capitale <= 0) {
								System.out.println ("Bisogna investire una somma maggiore di 0");
								ok = false;
							}
						}
					} while (!ok);
					
					int anni;
					
					do {
						menuDurataInvestimento();
						System.out.println ("Scegli la durata dell'investimento.");
						durata = tastiera.next().charAt(0);
					} while (scelta < '1' || scelta > '3'); 
					
					if (scelta == '1') {
						anni = 1;
					} else {
						if (scelta == '2') {
							anni = 5;
						} else {
							anni = 10;
						}
					}
					
					anniToMesi = anni * 12;
					
					do {
						menuRiskLevel();
						System.out.println ("Scegli il margine di rischio e guadagno.");
						rischio = tastiera.next().charAt(0);
					} while (scelta < '1' || scelta > '3');
					
					int percentualeHighRisk = 0;
					int probabilitaGuadagnare;
					int probabilitaUguale;
					int probabilitaPerdere;
					int percentualeGuadagno;
					int percentualePerdita;
					
					switch (durata) {
						case '1': {
							
							//Le probabilita' sono in percentuale
							probabilitaGuadagnare = 95;
							probabilitaUguale = 4;
							probabilitaPerdere = 1;
							percentualeGuadagno = 3;
							percentualePerdita = 2;
							
							capitale = investire (capitale, probabilitaGuadagnare, probabilitaUguale, probabilitaPerdere, 
													percentualeGuadagno, percentualeHighRisk, percentualePerdita, anni);

							System.out.println("Premere un tasto per continuare...");
							new java.util.Scanner(System.in).nextLine();
							break;
						}
						case '2': {
							
							//Le probabilita' sono in percentuale
							probabilitaGuadagnare = 65;
							probabilitaUguale = 20;
							probabilitaPerdere = 15;
							percentualeGuadagno = 15;
							percentualePerdita = 10;
							
							capitale = investire (capitale, probabilitaGuadagnare, probabilitaUguale, probabilitaPerdere, 
													percentualeGuadagno, percentualeHighRisk, percentualePerdita, anni);
						
							System.out.println("Premere un tasto per continuare...");
							new java.util.Scanner(System.in).nextLine();
							break;
						}
						case '3': {
							
							//Le probabilita' sono in percentuale
							probabilitaGuadagnare = 50;
							probabilitaUguale = 10;
							probabilitaPerdere = 40;
							percentualeGuadagno = 60;
							percentualePerdita = 45;
							percentualeHighRisk = 130;
							
							capitale = investire (capitale, probabilitaGuadagnare, probabilitaUguale, probabilitaPerdere, 
													percentualeGuadagno, percentualeHighRisk, percentualePerdita, anni);
						
							System.out.println("Premere un tasto per continuare...");
							new java.util.Scanner(System.in).nextLine();
							break;
						}
					}
					
					//questo lo metto qua o nel case '6'?
					for (int i=0; i < anniToMesi; i++) {
						portafoglio += 1000;
						mesi++;
					}
					
					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					//break;
				}
				case '4':{
					
					System.out.println("Quantita' di soldi in banca: " + soldiBanca);
					
					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '5':{
					
					System.out.println("Quantita' di soldi in banca: " + portafoglio);
					
					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '6':{
					
					portafoglio += 100;
					mesi++;
					primoInvestimentoMese = false;
					
					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '7':{
					
					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
			}
		} while (scelta != '0' );
	}

}
