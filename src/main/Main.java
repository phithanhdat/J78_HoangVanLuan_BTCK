package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		DataIO.root = "C:\\Users\\DELL\\Desktop\\Java\\J78\\BTCK\\";
		List<Book> listBook = DataIO.loadFile("book_data_en.txt");
		Scanner sc = new Scanner(System.in);
		int chon1 = -1;
		do {
			try {
				printMainMenu();
				System.out.print("Nhap lua chon: ");
				chon1 = sc.nextInt();
				switch (chon1) {
				case 1:
					hienThi(listBook);
					break;
				case 2:
					timKiem(listBook);
					break;
				case 3:
					themSach(listBook);
					break;
				case 4:
					xoaSach(listBook);
					break;
				case 5:
					suaSach(listBook);
					break;
				case 0:
					break;
				default:
					System.out.println("Nhap sai");
				}
			} catch (Exception e) {
				System.out.println("Loi: " + e.getMessage());
				sc = new Scanner(System.in);
			}
		} while (chon1 != 0);

	}

	public static void saveBooksToFile(List<Book> listBook) {
		String filePath = DataIO.root + "book_data.txt";
		BufferedWriter buffW = null;
		FileWriter fwriter = null;
		try {
			fwriter = new FileWriter(filePath);
			buffW = new BufferedWriter(fwriter);
			for (Book book : listBook) {
				String dataLine = book.getCode() + ";" + book.getTitle() + ";" + book.getAuthor() + ";"
						+ book.getCategory() + ";" + book.getYear();
				System.out.println("Dataline = " + dataLine);
				buffW.write(dataLine);
				buffW.newLine();
			}
			System.out.println("Dữ lieu dã dược cap nhat vào tep.");
		} catch (IOException e) {
			System.out.println("Lỗi khi lưu dữ lieu vào tep.");
		} finally {
			try {
				if (buffW != null)
					buffW.close();
			} catch (Exception e) {
			}
		}
	}

	private static void suaSach(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma sach can sửa: ");
		String code = sc.nextLine();

		boolean check = false;

		for (Book book : listBook) {
			if (book.getCode().equals(code)) {
				System.out.print("Nhap tieu de sach: ");
				String title = sc.nextLine();

				System.out.print("Nhap ten tac gia: ");
				String author = sc.nextLine();

				System.out.print("Nhap the loai: ");
				String category = sc.nextLine();

				System.out.print("Nhap nam xuat ban: ");
				String year = sc.nextLine();

				book.title = title;
				book.author = author;
				book.category = category;
				book.year = year;

				saveBooksToFile(listBook);
				System.out.println("Cap nhap thành cong.");
				check = true;
				break;
			}
		}

		if (!check) {
			System.out.println("Khong tim thay ma sach can cap nhap.");
		}
	}

	public static void xoaSach(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma sach: ");
		String code = sc.nextLine();
		boolean check = false;
		for (Book book : listBook) {
			if (book.getCode().equalsIgnoreCase(code)) {
				listBook.remove(book);
				System.out.println("Sach dã dược xóa thành cong.");
				check = true;
				saveBooksToFile(listBook);
				break;
			}
		}

		if (!check) {
			System.out.println("Khong tim thay sach.");
		}
	}

	private static void themSach(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Nhap ma sach: ");
		String _code = sc.nextLine();
		boolean check = false;
		for (Book book : listBook) {
			if (book.getCode().equals(_code)) {
				System.out.println("ma sach dã tồn tai.");
				check = true;
				break;
			}
		}
		if (!check) {
			System.out.print("Nhap ten sach: ");
			String _title = sc.nextLine();

			System.out.print("Nhap ten tac gia: ");
			String _author = sc.nextLine();

			System.out.print("Nhap the loai: ");
			String _category = sc.nextLine();

			System.out.print("Nhap nam xuat ban: ");
			String _year = sc.nextLine();

			Book book = new Book(_code, _title, _author, _category, _year);
			listBook.add(book);
			System.out.println("Them thành cong.");
			saveBooksToFile(listBook);

		}
	}

	private static void hienThi(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		int chon;
		do {
			printShowMenu();
			System.out.println("Nhap lua chon:");
			chon = sc.nextInt();
			switch (chon) {
			case 1:
				showMa(listBook);
				break;
			case 2:
				showTenTua(listBook);
				break;
			case 3:
				showTacGia(listBook);
				break;
			case 4:
				showTheLoai(listBook);
				break;
			case 5:
				showNamXB(listBook);
				break;
			case 0:
				break;
			default:
				System.out.println("Nhap sai");
			}
		} while (chon != 0);
	}

	private static void displayBookList(List<Book> listBook) {
		if (listBook.isEmpty()) {
			System.out.println("Khong tim thay sach nào.");
		} else {
			Book.printColumnTitle();
			for (Book book : listBook) {
				book.printBookInfo();
			}
		}
	}

	private static void showNamXB(List<Book> listBook) {
		List<Book> sortedBooks = new ArrayList<>(listBook);
		Collections.sort(sortedBooks, Comparator.comparing(Book::getYear));
		displayBookList(sortedBooks);

	}

	private static void showTheLoai(List<Book> listBook) {
		List<Book> sortedBooks = new ArrayList<>(listBook);
		Collections.sort(sortedBooks, Comparator.comparing(Book::getCategory));
		displayBookList(sortedBooks);

	}

	private static void showTacGia(List<Book> listBook) {
		List<Book> sortedBooks = new ArrayList<>(listBook);
		Collections.sort(sortedBooks, Comparator.comparing(Book::getAuthor));
		displayBookList(sortedBooks);

	}

	private static void showTenTua(List<Book> listBook) {
		List<Book> sortedBooks = new ArrayList<>(listBook);
		Collections.sort(sortedBooks, Comparator.comparing(Book::getTitle));
		displayBookList(sortedBooks);

	}

	private static void showMa(List<Book> listBook) {
		List<Book> sortedBooks = new ArrayList<>(listBook);

		Collections.sort(sortedBooks, new Comparator<Book>() {
			@Override
			public int compare(Book book1, Book book2) {
				int code1 = Integer.parseInt(book1.getCode());
				int code2 = Integer.parseInt(book2.getCode());
				return Integer.compare(code1, code2);
			}
		});

		displayBookList(sortedBooks);
	}

	private static void timKiem(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		int chon2;
		do {
			printSearchMenu();
			System.out.println("Nhap lua chon:");
			chon2 = sc.nextInt();
			switch (chon2) {
			case 1:
				theoMa(listBook);
				break;
			case 2:
				theoTuaSach(listBook);
				break;
			case 3:
				theoTenTacGia(listBook);
			case 0:
				break;
			default:
				System.out.println("Nhap sai");
			}
		} while (chon2 != 0);
	}

	private static void theoTenTacGia(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ten tac gia:");
		String author = sc.nextLine();
		boolean check = false;
		for (Book book : listBook) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				if (!check) {
					System.out.println("Tim thay sach");
					check = true;
				}
				System.out.println(book);
			}
		}
		if (!check) {
			System.out.println("Khong tim thay sach yeu cau");
		}

	}

	private static void theoTuaSach(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap tua sach :");
		String keyword = sc.nextLine();
		List<Book> result = new ArrayList<Book>();
		for (Book book : listBook) {
			String title = book.getTitle().toLowerCase();
			keyword = keyword.toLowerCase();
			if (title.contains(keyword)) {
				result.add(book);
			}
		}

		if (result.size() > 0) {
			displayBookList(result);
		} else {
			System.out.println("Khong tim thay cuon sach nao voi tu khoa: " + keyword);
		}

	}

	private static void theoMa(List<Book> listBook) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap ma sach: ");
		String code = sc.nextLine();
		boolean check = false;
		for (Book book : listBook) {
			if (book.getCode().equals(code)) {
				System.out.println("Tim thay sach");
				System.out.println(book);
				check = true;
			}
		}
		if (!check) {
			System.out.println("Khong tim thay sach yeu cau");
		}
	}

	public static void printMainMenu() {
		System.out.println("------------MENU-------------");
		System.out.println("1.Hien thi danh sach");
		System.out.println("2.Tim kiem");
		System.out.println("3.Them sach");
		System.out.println("4.Xoa sach");
		System.out.println("5.Sua thong tin");
		System.out.println("0.Thoat");
	}

	public static void printSearchMenu() {
		System.out.println("-----SEARCH-----");
		System.out.println("1.Tim theo ma");
		System.out.println("2.Tim theo tua sach");
		System.out.println("3.Tim theo ten tac gia");
		System.out.println("0.Quay lai");
	}

	public static void printShowMenu() {
		System.out.println("--------SHOW-------");
		System.out.println("1.Theo ma :");
		System.out.println("2.Theo ten tua A-Z :");
		System.out.println("3.Theo ten tac gia A-Z :");
		System.out.println("4.Theo the loai A-Z :");
		System.out.println("5.Theo nam xuat ban :");
		System.out.println("0.Quay lai");
	}
}
