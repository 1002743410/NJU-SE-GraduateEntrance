package chooseClassSystem;

import java.util.ArrayList;

/*
 * Class name:			Student
 * Method:				setPassword;setAccount;startWork;register;showCourseList;
 * 						selectCourse;quitCourse;showScore;chooseCommand;
 * Instance variable	account;password;studentLogin;
 */
public class Student {
	public static boolean studentLogin = false;// ��ʾ�Ƿ��ڵ�¼״̬
	private String account;// �����ʺ�

	public void setAccount(String account) {
		this.account = account;
	}

	public Student(String account) {
		this.setAccount(account);
	}

	/*
	 * ���������ʶ��
	 */
	public String chooseCommand(String command) {
		String[] commandSplit = command.split(" ");// �ָ�����
		int length = commandSplit.length;// ��������εĸ���
		String toReturn = null;
		if ((length == 2) && commandSplit[0].equals("Show")
				&& commandSplit[1].equals("mycourseList")) {// Show
															// mycourseListָ��
			toReturn = this.showCourseList();
		} else if ((length == 3) && commandSplit[0].equals("Select")
				&& commandSplit[1].equals("course")) {// Selectָ��
			toReturn = this.selectCourse(commandSplit[2]);
		} else if ((length == 3) && commandSplit[0].equals("Quit")
				&& commandSplit[1].equals("course")) {// Quitָ��
			toReturn = this.quitCourse(commandSplit[2]);
		} else if ((length == 2) && commandSplit[0].equals("Show")
				&& commandSplit[1].equals("score")) {// Show scoreָ��
			toReturn = this.showScore();
		} else if ((length == 2) && commandSplit[0].equals("Show")
				&& commandSplit[1].equals("CouseList")) {
			toReturn = this.showCourses();
		}
		return toReturn;
	}

	private String showCourses() {
		ArrayList<String> courseList = IOHelper.readFile("CourseList.txt");
		String toReturn = "";
		for (String m : courseList) {
			toReturn = toReturn + m + "\t";
		}
		return toReturn;
	}

	/*
	 * չʾ��ѡ�Ŀγ��б�
	 */
	private String showCourseList() {
		ArrayList<String> studentInfo = IOHelper
				.readFile("ChooseCourseList.txt");
		ArrayList<String> courseInfo = IOHelper.readFile("CourseList.txt");
		String toReturn = "";
		for (String m : studentInfo) {
			String[] infoLine = m.split(" ");
			int length = infoLine.length;
			if (infoLine[0].equals(account)) {
				if (length > 1) {
					for (int i = 1; i < length; i++) {
						String courseNum = infoLine[i].split(";")[0];
						for (int j = 0; j < courseInfo.size(); j++) {
							if (courseInfo.get(j).split(" ")[0]
									.equals(courseNum)) {
								toReturn = toReturn + courseInfo.get(j) + "\t";
							}
						}
					}
				} else {
					toReturn = "δѡ���κογ�.";
				}
			}
		}
		return toReturn;
	}

	private String selectCourse(String courseNum) {
		if (IOHelper.checkAccount(courseNum, "CourseList.txt")) {
			ArrayList<String> chooseInfo = IOHelper
					.readFile("ChooseCourseList.txt");
			int size = chooseInfo.size();
			boolean ifChoose = false;
			for (int j = 0; j < size; j++) {
				String[] chooseInfoLine = chooseInfo.get(j).split(" ");
				int length = chooseInfoLine.length;
				String afterSelect = chooseInfo.get(j);
				for (int i = 0; i < length; i++) {
					if ((chooseInfoLine[0].equals(this.account))
							&& (chooseInfoLine[i].split(";")[0]
									.equals(courseNum))) {
						ifChoose = true;
					}
				}
				if ((chooseInfoLine[0].equals(this.account))
						&& (ifChoose == false)) {
					afterSelect = afterSelect + " " + courseNum;
				}
				chooseInfo.set(j, afterSelect);
			}
			IOHelper.writeFile("ChooseCourseList.txt", chooseInfo);
			if (ifChoose == false) {
				return "ѡ�γɹ�.";
			} else {
				return "���Ѿ�ѡ����ÿγ�!";
			}
		} else {
			return "�����ڸÿγ̺ŵĿγ�!";
		}
	}

	private String quitCourse(String courseNum) {
		if (IOHelper.checkAccount(courseNum, "CourseList.txt")) {
			ArrayList<String> chooseInfo = IOHelper
					.readFile("ChooseCourseList.txt");
			boolean ifChoose = false;
			int size = chooseInfo.size();
			for (int j = 0; j < size; j++) {
				String afterQuit = "";
				String[] chooseInfoLine = chooseInfo.get(j).split(" ");
				int length = chooseInfoLine.length;
				for (int i = 0; i < length; i++) {
					if ((chooseInfoLine[0].equals(this.account))
							&& (chooseInfoLine[i].split(";")[0]
									.equals(courseNum))) {
						ifChoose = true;
					} else {
						afterQuit = afterQuit + chooseInfoLine[i] + " ";
					}
				}
				chooseInfo.set(j, afterQuit);
			}
			IOHelper.writeFile("ChooseCourseList.txt", chooseInfo);
			if (ifChoose == true) {
				return "��ѡ�ɹ�!";
			} else {
				return "��������δѡ��ÿγ�!";
			}
		} else {
			return "�����ڸÿγ̺ŵĿγ�!";
		}
	}

	private String showScore() {
		String toReturn = "";
		ArrayList<String> chooseInfo=IOHelper.readFile("ChooseCourseList.txt");
		for(String m:chooseInfo){
			if(m.split(" ")[0].equals(account)&&m.split(" ").length>1){
				String msplit[]=m.split(" ");
				for(int i=1;i<msplit.length;i++){
					toReturn=toReturn+msplit[i]+" ";
				}
			}
		}
		return toReturn;
	}

}
