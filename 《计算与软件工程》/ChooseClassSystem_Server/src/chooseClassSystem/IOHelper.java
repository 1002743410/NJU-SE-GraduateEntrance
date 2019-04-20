package chooseClassSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOHelper {
	public static void writeFile(String location, ArrayList<String> toWrite) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					location)));
			for (String m : toWrite) {
				writer.write(m);
				writer.write("\r\n");
			}
			writer.close();
		} catch (IOException e) {
		} finally {
		}
	}

	public static ArrayList<String> readFile(String location) {
		ArrayList<String> toReturn = new ArrayList<String>();// ���ڴ�Ŷ�ȡ��������
		String nextLine = null;
		try {
			FileReader reader = new FileReader(location);
			BufferedReader br = new BufferedReader(reader);
			while ((nextLine = br.readLine()) != null) {
				toReturn.add(nextLine);
			}
			br.close();
			reader.close();
		} catch (IOException e) {
		} finally {
		}
		return toReturn;
	}

	/*
	 * ���������ʺ��Ƿ��Ѿ���ע��
	 */
	public static boolean checkAccount(String account, String location) {
		boolean toReturn = false;// ������������Ƿ��Ѿ������ʺŵķ���ֵ
		ArrayList<String> accountPassword = null;// ��Ŷ�ȡ�����ʺ�
		accountPassword = IOHelper.readFile(location);
		/*
		 * У���û�������
		 */
		int Size = accountPassword.size();
		for (int i = 0; i < Size; i++) {
			String accountPasswordToString = accountPassword.get(i);
			String[] accountPasswordString = accountPasswordToString.split(" ");
			String accountToCheck = accountPasswordString[0];
			if (accountToCheck.equals(account)) {
				toReturn = true;
				break;
			}
		}
		return toReturn;
	}

	/*
	 * �����û����������Ƿ���ȷ
	 */
	public static boolean checkAccountPassword(String account, String password,
			String location) {
		boolean toReturn = false;// ���������ʺ��Ƿ���ȷ
		ArrayList<String> accountPassword = null;// ���ڴ�Ŷ�ȡ�����ʺ���������
		accountPassword = IOHelper.readFile(location);
		/*
		 * У���ʺ�����
		 */
		int Size = accountPassword.size();
		for (int i = 0; i < Size; i++) {
			String accountPasswordToString = accountPassword.get(i);
			String[] accountPasswordString = accountPasswordToString.split(" ");
			int length = accountPasswordString.length;
			if (length > 1) {
				String accountToCheck = accountPasswordString[0];
				String passwordToCheck = accountPasswordString[1];
				if ((accountToCheck.equals(account))
						&& (passwordToCheck.equals(password))) {
					toReturn = true;
					break;
				}
			}
		}
		return toReturn;// ���ؼ�����
	}
}
