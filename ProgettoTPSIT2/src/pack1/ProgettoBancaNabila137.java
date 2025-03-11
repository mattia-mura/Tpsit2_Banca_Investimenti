package pack1;
import java.util.Scanner;

public class ProgettoBancaNabila137 {
		static double soldiBanca = 0;
		static double portafoglio = 0;
		static int mesi = 0;

		public static void menuPrincipale() {
			System.out.println("-----MENU-----");
			System.out.println("1. DEPOSITARE");
			System.out.println("2. PRELEVARE");
			System.out.println("3. INVESTIRE");
			System.out.println("4. Visualizzare lo stato del conto in banca");
			System.out.println("5. Visualizzare lo stato del portafoglio");
			System.out.println("6. Mese successivo\n");

			System.out.println("0. Esci.");
		}

		public static void menuDurataInvestimento() {
			System.out.println("-----MENU DURATA DELL'INVESTIMENTO-----");
			System.out.println("1. BREVE");
			System.out.println("2. MEDIA");
			System.out.println("3. LUNGA");
		}

		public static void menuRiskLevel() {
			System.out.println("-----MARGINI DI RISCHIO E GUADAGNO-----");
			System.out.println("1. BASSO");
			System.out.println("2. MEDIO");
			System.out.println("3. ALTO");
		}

		/*public static boolean depositare(double depositMoney) {

			if ((depositMoney <= portafoglio) && (depositMoney > 0)) {
				portafoglio -= depositMoney;
				soldiBanca += depositMoney;
				return true;
			}

			return false;
		}

		public static boolean prelevare(double withdrawals) {

			if ((withdrawals <= soldiBanca) && (withdrawals > 0)) {
				soldiBanca -= withdrawals;
				portafoglio += withdrawals;
				return true;
			}

			return false;
		}*/
		
		public static boolean depositPreleva (double money, double confronto) {
			
			if ((money <= confronto) && (money > 0)) {
				portafoglio += money;
				soldiBanca -= money;
				return true;
			}
			return false;
			
		}
		
		public static double money (double x) {
			
			return x*(-1);
			
		}

		public static double investimento(double capitale, int probabilitaGuadagno, int rangeGuadagno[],
				int rangePerdita[]) {

			int probabilita = (int) (Math.random() * 100 + 1);

			if (probabilita <= probabilitaGuadagno) {
				int percentualeGuadagno = (int) (Math.random() * (rangeGuadagno[1] - rangeGuadagno[0] + 1)
						+ rangeGuadagno[0]);
				capitale *= 1 + (double) percentualeGuadagno / 100;
			} else {
				int percentualePerdita = (int) (Math.random() * (rangePerdita[1] - rangePerdita[0] + 1) + rangePerdita[0]);
				capitale *= 1 - (double) percentualePerdita / 100;
			}

			return capitale;
		}

		public static void main(String[] args) {
			Scanner tastiera = new Scanner(System.in);
			char scelta;
			double capitale = 0;
			int nMaxInvestimenti = 5;
			double returnsHolder[] = new double[nMaxInvestimenti];
			int anni[] = new int[nMaxInvestimenti]; // durate degli investimenti
			int tempMesi[] = new int[nMaxInvestimenti]; // mi serve per tenere traccia dell'inizio di ogni investimento
			int contAnni = 0;

			do {
				do {
					if (mesi == 0) {
						System.out.println("- - - Benvenuti alla nostra banca - - -");
						System.out.println("Non hai soldi nel tuo portafoglio.");
						System.out.println("Desideri visualizzaeo lo stato del tuo account? o venire il mese successivo?");
					}
					menuPrincipale();
					System.out.println("Cosa vuoi fare?");
					scelta = tastiera.next().charAt(0);
				} while (scelta < '0' || scelta > '6');

				switch (scelta) {
				case '1': {

					double depositMoney = -1.0;
					boolean ok;

					if (portafoglio != 0) {
						do {
							ok = true;
							System.out.println("Quanti soldi desideri depositare?");
							tastiera.nextLine();
							String stringa = tastiera.nextLine();
							stringa.trim();
							try {
								depositMoney = Double.parseDouble(stringa);
							} catch (NumberFormatException e) {
								System.out.println("Formato non e' double.");
								ok = false;
							}
						} while (!ok);
						
						depositMoney = money(depositMoney);
						double contoConfronto = portafoglio;

						if (!depositPreleva(depositMoney, contoConfronto)) {
							System.out.println("Soldi non sufficiente nel portafoglio.");
							System.out.println("Scegliere una cifra minore o aspettare per il mese successivo.");
						} else {
							System.out.println("Il deposito e' andato a buon fine.");
						}
					} else {
						System.out.println("Non hai soldi nel portafoglio.");
					}

					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '2': {

					double withdrawals = -1;
					boolean ok;

					if (soldiBanca <= 0) {
						System.out.println("Non hai soldi in banca. Deposita.");
					} else {
						do {
							ok = true;
							System.out.println("Quanti soldi desideri prelevare?");
							tastiera.nextLine();
							String stringa = tastiera.nextLine();
							stringa.trim();
							try {
								withdrawals = Double.parseDouble(stringa);
							} catch (NumberFormatException e) {
								System.out.println("Formato non e' double.");
								ok = false;
							}
						} while (!ok);
						
						double contoConfronto = soldiBanca;

						if (!depositPreleva(withdrawals, contoConfronto)) {
							System.out.println("Non hai soldi sufficiente in banca.");
							System.out.println("Prova a diminuire la somma o depositare soldi.");
						} else {
							System.out.println("Il prelievo e' andato a buon fine.");
						}
					}

					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '3': {

					char durata;
					capitale = 0;
					boolean ok;
					char rischio;

					if (contAnni == nMaxInvestimenti) {
						System.out.println(
								"Non puoi fare altri investimenti. Hai gia fatto " + nMaxInvestimenti + " investimenti.");
					} else {
						if (soldiBanca <= 0) {
							System.out.println("Non hai soldi in banca. Deposita.");
						} else {
							do {
								ok = true;
								System.out.println("Somma da investire: ");
								tastiera.nextLine();
								String stringa = tastiera.nextLine();
								stringa.trim();
								try {
									capitale = Double.parseDouble(stringa);
								} catch (NumberFormatException e) {
									System.out.println("Formato non corretto.");
									ok = false;
								}
								if (ok) {
									if (capitale <= 0) {
										System.out.println("Bisogna investire una somma maggiore di 0");
										ok = false;
									}
								}
							} while (!ok);

							soldiBanca -= capitale;

							do {
								menuDurataInvestimento();
								System.out.println("Scegli la durata dell'investimento.");
								durata = tastiera.next().charAt(0);
							} while (durata < '1' || durata > '3');

							if (durata == '1') {
								anni[contAnni] = 1;
							} else {
								if (durata == '2') {
									anni[contAnni] = 5;
								} else {
									anni[contAnni] = 10;
								}
							}

							do {
								menuRiskLevel();
								System.out.println("Scegli il margine di rischio e guadagno.");
								rischio = tastiera.next().charAt(0);
							} while (rischio < '1' || rischio > '3');

							int probabilitaGuadagno = 0;
							int percentualeMinMaxGuadagno[] = new int[2];
							int percentualeMinMaxPerdita[] = new int[2];

							switch (rischio) {
							case '1': {

								// Le probabilita' sono in percentuale
								probabilitaGuadagno = 95;
								percentualeMinMaxGuadagno[0] = 5;
								percentualeMinMaxGuadagno[1] = 10;
								percentualeMinMaxPerdita[0] = 5;
								percentualeMinMaxPerdita[1] = 8;

								System.out.println("Premere un tasto per continuare...");
								new java.util.Scanner(System.in).nextLine();
								break;
							}
							case '2': {

								probabilitaGuadagno = 60;
								percentualeMinMaxGuadagno[0] = 20;
								percentualeMinMaxGuadagno[1] = 30;
								percentualeMinMaxPerdita[0] = 15;
								percentualeMinMaxPerdita[1] = 25;

								System.out.println("Premere un tasto per continuare...");
								new java.util.Scanner(System.in).nextLine();
								break;
							}
							case '3': {

								probabilitaGuadagno = 40;
								percentualeMinMaxGuadagno[0] = 60;
								percentualeMinMaxGuadagno[1] = 85;
								percentualeMinMaxPerdita[0] = 50;
								percentualeMinMaxPerdita[1] = 125;

								System.out.println("Premere un tasto per continuare...");
								new java.util.Scanner(System.in).nextLine();
								break;
							}
							}
							capitale = investimento(capitale, probabilitaGuadagno, percentualeMinMaxGuadagno,
									percentualeMinMaxPerdita);

							tempMesi[contAnni] = 0;
							returnsHolder[contAnni] = capitale;
							contAnni++;

						}
					}

					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '4': {

					System.out.println("Quantita' di soldi in banca: " + soldiBanca);

					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '5': {

					System.out.println("Quantita' di soldi nel portafoglio: " + portafoglio);

					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				case '6': {

					portafoglio += 100;
					mesi++;

					for (int i = 0; i < contAnni;) {
						tempMesi[i]++;
						if (tempMesi[i] == anni[i] * 12) {
							soldiBanca += returnsHolder[i];
							for (int j = i; j < contAnni - 1; j++) {
								anni[j] = anni[j + 1];
								returnsHolder[j] = returnsHolder[j + 1];
								tempMesi[j] = tempMesi[j + 1];
							}
							contAnni--;

							System.out.println("E' terminato il periodo del tuo investimento.");
							char carattere;
							do {
								System.out.println(
										"Vuoi vedere quanto hai ricevuto? (premi 's' per dire \"si\" o 'n' per dire \"no\")");
								carattere = tastiera.next().charAt(0);
							} while (carattere != 's' && carattere != 'n');
							if (carattere == 's') {
								System.out.println("Risultato dell'investimento: " + returnsHolder[i]);
							}

						} else {
							i++;
						}
					}

					System.out.println("Premere un tasto per continuare...");
					new java.util.Scanner(System.in).nextLine();
					break;
				}
				}
			} while (scelta != '0');
			tastiera.close();
		}

}
