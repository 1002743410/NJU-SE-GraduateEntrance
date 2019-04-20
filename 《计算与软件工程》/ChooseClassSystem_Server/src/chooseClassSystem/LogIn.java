package chooseClassSystem;

/*                        
 * Version information     Version 2
 * author                  Peiwen Sun 121250134
 * Date                    2013.5.16
 */

/*
 * Class name              LogIn
 * Method                  main;readCommand;chooseMode;checkAccountPassword;
 * Instance variable       no instance variable
 */
public class LogIn {
	/*
	 * �������������,�����������С�
	 */
	public String chooseMode(String Receive) {
		boolean error = true; // �½�һ�����������������Ƿ��Ѿ�����

		/*
		 * ������ĳ��Ƚ��м���
		 */
		String[] afterSplit = Receive.split(" ");
		int length = afterSplit.length;
		if ((length != 3) && (length != 4)) {
			error = false;
		}

		/*
		 * ���ڹ���Ա��¼��ָ��
		 */
		if ((length == 3) && (error == true)) {
			String command = afterSplit[0]; // �����
			String account = afterSplit[1]; // �ʺŲ���
			String password = afterSplit[2]; // ���벿��
			if ((command.equals("Login")) && (account.equals("admin"))) {
				Administrator.administratorLogin = IOHelper
						.checkAccountPassword(account, password,
								"Administrator.txt");
				if (Administrator.administratorLogin == false) {
					error = false;
				}
			} else {
				error = false;
			}
		}

		/*
		 * ���ڽ�ʦ��ѧ����ָ��
		 */
		if ((length == 4) && (error == true)) {
			String command = afterSplit[0]; // �����
			String mode = afterSplit[1]; // ģʽ����
			String account = afterSplit[2]; // �ʺŲ���
			String password = afterSplit[3];// ���벿��
			/*
			 * �������ǰ�����Ƿ���ȷ������ǰ����ֱ�����¼��ע�����ѧ������ʦ����
			 */
			if ((!(command.equals("Login") || command.equals("Register")))
					|| (!(mode.equals("Teacher") || mode.equals("Student")))) {// ���������
				error = false;
			} else if ((command.equals("Login")) && (error == true)) {
				if (mode.equals("Teacher")
						&& (IOHelper.checkAccountPassword(account, password,
								"TeacherList.txt"))) {// ��Ϊ��ʦ��¼���ʺ�������ȷ
					Teacher.teacherLogin = true;
				} else if (mode.equals("Student")
						&& (IOHelper.checkAccountPassword(account, password,
								"StudentList.txt"))) {// ��Ϊѧ����¼���ʺ�������ȷ
					Student.studentLogin = true;
				} else {// ���������
					error = false;
				}
			}
		}
		if(error==false){
			return "false";
		}else{
			return "true";
		}
	}

}
