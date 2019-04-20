package chooseClassSystem;

import java.util.ArrayList;

/*
 * Class name              Administrator
 * Method                  setPassword;setAccount;chooseCommand;changePassword;delete;show;startWork;
 * Instance variable       administratorLogin;account;password;
 */

public class Administrator {
	private final String account = "admin";
	private String password;
	public static boolean administratorLogin = false;

	public void setPassword(String password) {
		this.password = password;
	}

	public Administrator(String password) {
		this.setPassword(password);
	}

	/*
	 * ���������ʶ��
	 */
	public String chooseCommand(String command) {
		String[] commandSplit = command.split(" ");// ��ָ����зָ���
		int length = commandSplit.length; // ָ��ĳ���
		String toReturn = null;
		if ((length == 2) && (commandSplit[0].equals("Show"))) {// show����
			toReturn = this.show(commandSplit[1]);
		} else if ((length == 3)
				&& (commandSplit[0].equals("ChangePassword") && commandSplit[1]
						.equals("admin"))) {// ChangPassword����
			password = commandSplit[2];
			toReturn = this.changePassword(password);
		} else if ((length == 3) && (commandSplit[0].equals("Delete"))) {// Delete����
			toReturn = this.delete(commandSplit[1], commandSplit[2]);
		} else {// �������
			toReturn = "fail";
		}
		return toReturn;
	}

	/*
	 * �޸�����
	 */
	private String changePassword(String NewPassword) {
		ArrayList<String> accountPassword = null;// ���ڴ�Ŷ�ȡ�����ʺ�����
		/*
		 * ��ȡ�ʺ�����
		 */
		accountPassword = IOHelper.readFile("Administrator.txt");
		/*
		 * �����ʺţ����޸Ķ�Ӧ����
		 */
		int Size = accountPassword.size();
		for (int i = 0; i < Size; i++) {
			String accountPasswordToString = accountPassword.get(i);
			String[] accountPasswordString = accountPasswordToString.split(" ");
			String accountToCheck = accountPasswordString[0];
			if (accountToCheck.equals(account)) { // �޸����벢д��
				String afterChange = account + " " + NewPassword;
				accountPassword.set(i, afterChange);
				break;
			}
		}
		IOHelper.writeFile("Administrator.txt", accountPassword);
		return "true";
	}

	/*
	 * �鿴�����б�
	 */
	private String show(String listName) {
		String location = null;// �趨һ���ַ������ڴ�������ı�λ��
		if (listName.equals("��ʦ�б�")) {
			location = "TeacherList.txt";
		} else if (listName.equals("ѧ���б�")) {
			location = "StudentList.txt";
		} else if (listName.equals("�γ��б�")) {
			location = "CourseList.txt";
		} else if (listName.equals("ѡ���б�")) {
			location = "ChooseCourseList.txt";
		}
		ArrayList<String> toPrint = IOHelper.readFile(location);
		String toReturn = "";
		for (String s : toPrint) {
			toReturn = toReturn + s + "\t";
		}
		return toReturn;
	}

	/*
	 * ɾ�������б��е�ĳ��Ԫ��
	 */
	private String delete(String listName, String number) { // ɾ�������б�
		boolean error = true;// �趨һ�����ڼ�������Ƿ����Ĳ�������
		String location = null;// �趨һ���ַ������ڴ�������ı�λ��
		if (listName.equals("��ʦ�б�")) {
			location = "TeacherList.txt";
		} else if (listName.equals("ѧ���б�")) {
			location = "StudentList.txt";
		} else if (listName.equals("�γ��б�")) {
			location = "CourseList.txt";
		} else if (listName.equals("ѡ���б�")) {
			location = "ChooseCourseList.txt";
		} else {
			error = false;
		}
		if (error == true) {
			ArrayList<String> accountPassword = null;
			/*
			 * ���ļ��ж�������
			 */
			accountPassword = IOHelper.readFile(location);
			int numberInt = Integer.parseInt(number) - 1;
			accountPassword.remove(numberInt); // ɾ��ָ����
			/*
			 * ����д��
			 */
			IOHelper.writeFile(location, accountPassword);
			return "true";
		} else {
			return "false";
		}

	}

}
