package com.atguigu.p2;

public class CustomerView {
	private CustomerList customers = new CustomerList(10);

	public CustomerView() {
		Customer cust = new Customer("kevin", "man", 30, "2267891200",
				"abc@email.com");
		customers.addCustomer(cust);
	}

	public void enterMainMenu() {
		boolean loopFlag = true;
		do {
			System.out
					.println("\n-----------------customer relationship management-----------------\n");
			System.out.println("                   1 add customer");
			System.out.println("                   2 edit customer");
			System.out.println("                   3 delete customer");
			System.out.println("                   4 customer list");
			System.out.println("                   5 exit\n");
			System.out.print("                   choose(1-5)：");

			char key = CMUtility.readMenuSelection();
			System.out.println();
			switch (key) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomers();
				break;
			case '5':
				System.out.print("Please confirm(Y/N)：");
				char yn = CMUtility.readConfirmSelection();
				if (yn == 'Y')
					loopFlag = false;
				break;
			}
		} while (loopFlag);
	}

	private void addNewCustomer() {
		System.out.println("---------------------add customer---------------------");
		System.out.print("name：");
		String name = CMUtility.readString(4);
		System.out.print("gender：");
		char gender = CMUtility.readChar();
		System.out.print("age：");
		int age = CMUtility.readInt();
		System.out.print("phone number：");
		String phone = CMUtility.readString(15);
		System.out.print("email：");
		String email = CMUtility.readString(15);

		Customer cust = new Customer(name, gender, age, phone, email);
		boolean flag = customers.addCustomer(cust);
		if (flag) {
			System.out
					.println("---------------------finished---------------------");
		} else {
			System.out.println("----------------record is full,cannot be added-----------------");
		}
	}

	private void modifyCustomer() {
		System.out.println("---------------------edit customer---------------------");

		int index = 0;
		Customer cust = null;
		for (;;) {
			System.out.print("please select the number of customer(-1 exit)：");
			index = CMUtility.readInt();
			if (index == -1) {
				return;
			}

			cust = customers.getCustomer(index - 1);
			if (cust == null) {
				System.out.println("cannot find corresponding customer！");
			} else
				break;
		}

		System.out.print("name(" + cust.getName() + ")：");
		String name = CMUtility.readString(4, cust.getName());

		System.out.print("gender(" + cust.getGender() + ")：");
		char gender = CMUtility.readChar(cust.getGender());

		System.out.print("age(" + cust.getAge() + ")：");
		int age = CMUtility.readInt(cust.getAge());

		System.out.print("phone number(" + cust.getPhone() + ")：");
		String phone = CMUtility.readString(15, cust.getPhone());

		System.out.print("email(" + cust.getEmail() + ")：");
		String email = CMUtility.readString(15, cust.getEmail());

		cust = new Customer(name, gender, age, phone, email);

		boolean flag = customers.replaceCustomer(index - 1, cust);
		if (flag) {
			System.out
					.println("---------------------finished---------------------");
		} else {
			System.out.println("----------cannot find,edition fail--------------");
		}
	}

	private void deleteCustomer() {
		System.out.println("---------------------delete customer---------------------");

		int index = 0;
		Customer cust = null;
		for (;;) {
			System.out.print("please select the number of customer(-1 exit)：");
			index = CMUtility.readInt();
			if (index == -1) {
				return;
			}

			cust = customers.getCustomer(index - 1);
			if (cust == null) {
				System.out.println("cannot find correspnding customer");
			} else
				break;
		}

		System.out.print("please confirm(Y/N)：");
		char yn = CMUtility.readConfirmSelection();
		if (yn == 'N')
			return;

		boolean flag = customers.deleteCustomer(index - 1);
		if (flag) {
			System.out
					.println("---------------------finished---------------------");
		} else {
			System.out.println("----------Cannot find,delete fail--------------");
		}
	}

	private void listAllCustomers() {
        System.out.println("---------------------------customer list---------------------------");
        Customer[] custs = customers.getAllCustomers();
        if (custs.length == 0) {
            System.out.println("no record");
        } else {
            System.out.println("Number\tName\tGender\tAge\t\tPhone Number\t\tEmail");
            for (int i = 0; i < custs.length; i++) {
//            System.out.println(i + 1 + "\t" + custs[i].getName() + "\t" + custs[i].getGender() + "\t" + custs[i].getAge() + "\t\t" + custs[i].getPhone() + "\t" + custs[i].getEmail());
            	System.out.println((i+1) + "\t" + custs[i].getDetails());
            }
        }

        System.out.println("-------------------------customer list finished-------------------------");
    }

	public static void main(String[] args) {
		CustomerView cView = new CustomerView();
		cView.enterMainMenu();
	}
}
