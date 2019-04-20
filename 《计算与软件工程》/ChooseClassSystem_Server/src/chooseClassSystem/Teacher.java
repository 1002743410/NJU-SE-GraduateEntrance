package chooseClassSystem;

import java.util.ArrayList;

/*
 * Class name:			Teacher
 * Method:				setPassword;setAccount;startWork;register;publishCourse;
 * 						showCourse;updateCourse;showStudent;recordScore;chooseCommand;
 * Instance variable:	teacherLogin;account;password;
 */
public class Teacher {
	public static boolean teacherLogin = false;// ��ʾ�Ƿ��ڵ�¼״̬
	private String account;// �����ʺ�
	
	public void setAccount(String account) {
		this.account = account;
	}

	public Teacher(String account) {
		this.setAccount(account);
	}

	/*
	 * ���������ʶ��
	 */
	public String chooseCommand(String command) {
		String[] commandSplit = command.split(" ");// �ָ�����
		int length = commandSplit.length;// ��������εĸ���
		if ((length > 3) && commandSplit[0].equals("Publish")) {// Publish����
			return this.publishCourse(command);
		} else if ((length == 3) && commandSplit[0].equals("Show")
				&& commandSplit[1].equals("course")) {// Show course����
			return this.showCourse(commandSplit[2]);
		} else if ((length == 3) && commandSplit[0].equals("Show")
				&& commandSplit[1].equals("student")) {// Show student����
			return this.showStudent(commandSplit[2]);
		} else if ((length > 3) && commandSplit[0].equals("Record")
				&& commandSplit[1].equals("score")) {// Record score����
			return this.recordScore(command);
		} else if ((length > 3) && commandSplit[0].equals("Update")
				&& commandSplit[1].equals("course")) {
			return this.updateCourse(command);
		} else {
			return "";
		}
	}

	/*
	 * �����γ�
	 */
	private String publishCourse(String command) {
		String info = "";// ���ڴ�ſγ���Ϣ
		String split[] = command.split(" ");
		for (int i = 1; i < split.length; i++) {
			info = info + split[i] + " ";
		}
		if (info.length() != 0) {
			if (!IOHelper.checkAccount(info.split(" ")[0], "CourseList.txt")) {
				ArrayList<String> toWrite = IOHelper.readFile("CourseList.txt");
				toWrite.add(info);
				IOHelper.writeFile("CourseList.txt", toWrite);
				return "д��ɹ�.";
			} else {
				return "�γ̺��ѱ�ʹ�á�";
			}
		} else {
			return "������Ŀγ̺�Ϊ�գ�";
		}
	}

	/*
	 * �鿴��ʦ���˷����Ŀγ�
	 */
	private String showCourse(String courseNum) {
		if (courseNum.length() == 0) {// ����Ϊ��ʱ
			return "����Ϊ��.";
		} else if (courseNum.equals("*")) {// ����Ϊ"*"ʱ
			ArrayList<String> courseList = IOHelper.readFile("CourseList.txt");
			String toReturn = "";
			for (String m : courseList) {
				for (String n : m.split(" ")[7].split(";")) {
					if (n.equals(this.account)) {
						toReturn = toReturn + m + "\t";
					}
				}
			}
			return toReturn;
		} else if (IOHelper.checkAccount(courseNum, "CourseList.txt")) {// ����Ϊ�γ̺��Ҹÿγ̴���ʱ
			ArrayList<String> courseToShow = IOHelper
					.readFile("CourseList.txt");
			for (String m : courseToShow) {
				if (m.split(" ")[0].equals(courseNum)) {
					return m;
				}
			}
			return "������.";
		} else {// ����Ŀγ̲�����ʱ
			return "�����ڸÿγ̺ŵĿγ�.";
		}
	}

	/*
	 * �����ѷ����Ŀγ�
	 */
	private String updateCourse(String command) {
		String courseinfo = "";
		String[] split = command.split(" ");
		String courseToUpdate = split[2];
		for (int i = 3; i < split.length; i++) {
			courseinfo = courseinfo + split[i] + " ";
		}
		ArrayList<String> courseArray = IOHelper.readFile("CourseList.txt");
		int size = courseArray.size();
		int toUpdate = -1;
		for (int i = 0; i < size; i++) {
			String num = courseArray.get(i).split(" ")[0];
			if (num.equals(courseToUpdate)) {
				toUpdate = i;
			}
		}
		if (courseinfo.length() != 0) {
			if (!IOHelper.checkAccount(courseinfo, "CourseList.txt")) {
				courseArray.set(toUpdate, courseinfo);
				IOHelper.writeFile("CourseList.txt", courseArray);
				return "�������.";
			} else {
				return "�γ̺��ѱ�ʹ��.";
			}
		} else {
			return "������Ŀγ̺�Ϊ��.";
		}

	}

	private String showStudent(String courseNum) {
		String toReturn = "";
		if (courseNum.length() != 0) {
			if (IOHelper.checkAccount(courseNum, "CourseList.txt")) {
				ArrayList<String> chooseCourseList = IOHelper
						.readFile("ChooseCourseList.txt");
				for (String m : chooseCourseList) {
					for (String n : m.split(" ")) {
						if (n.split(";")[0].equals(courseNum)) {
							toReturn = toReturn + m.split(" ")[0];
							if (n.split(";").length > 1) {
								toReturn = toReturn + ";" + n.split(";")[1];
							}
							toReturn = toReturn + " ";
						}
					}
				}
				return toReturn;
			} else {
				return "�����ڸÿγ̺ŵĿγ�.";
			}
		} else {
			return "������Ŀγ̺�Ϊ��.";
		}
	}

	private String recordScore(String command) {
		String[] split = command.split(" ");
		String courseNum = split[2];
		String studentNum = split[3];
		String score = split[4];
		String toReturn = null;
		boolean studentFindIf = false;
		boolean courseFindIf = false;
		boolean haveRightIf = false;
		ArrayList<String> courseList = IOHelper.readFile("CourseList.txt");
		for (String line : courseList) {// ��ʦ�Ƿ��иÿγ�Ȩ��
			if (line.split(" ")[0].equals(courseNum)) {
				String info[] = line.split(" ")[7].split(";");
				for (String teacher : info) {
					if (teacher.equals(account)) {
						haveRightIf = true;
					}
				}
			}
		}
		if (IOHelper.checkAccount(courseNum, "CourseList.txt")) {// �ÿγ��Ƿ����
			if (haveRightIf == true) {// �Ƿ�ӵ��Ȩ��
				ArrayList<String> chooseCourseList = IOHelper
						.readFile("ChooseCourseList.txt");
				int size = chooseCourseList.size();
				for (int j = 0; j < size; j++) {
					String[] mSplit = chooseCourseList.get(j).split(" ");
					int length = mSplit.length;
					String afterRecord = "";
					if (mSplit[0].equals(studentNum)) {// �ÿγ����Ƿ��и�ѧ��
						studentFindIf = true;
						for (int i = 0; i < length; i++) {
							if (mSplit[i].split(";")[0].equals(courseNum)) {
								courseFindIf = true;
								if (mSplit[i].split(";").length == 1) {// �Ƿ��Ѿ�Ϊ��ѧ���Ǽ��˳ɼ�
									afterRecord = afterRecord + mSplit[i] + ";"
											+ score + " ";
									toReturn = "�ɹ�.";
								} else {
									afterRecord = afterRecord + mSplit[i] + " ";
									toReturn = "��ѧ���Ѿ��Ǽ��˳ɼ�.";
								}
							} else {
								afterRecord = afterRecord + mSplit[i] + " ";
							}
						}
						chooseCourseList.set(j, afterRecord);
					}
				}
				if (studentFindIf == false) {
					toReturn = "δ�ҵ���ѧ����.";
				}
				if (studentFindIf == true && courseFindIf == false) {
					toReturn = "��ѧ��δѡ��ÿγ�.";
				}
				IOHelper.writeFile("ChooseCourseList.txt", chooseCourseList);
			} else {
				toReturn = "��δӵ�иÿγ�Ȩ��.";
			}
		} else {
			toReturn = "�����ڸÿγ̺ŵĿγ�.";
		}

		return toReturn;
	}

}
