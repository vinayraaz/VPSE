package in.varadhismartek.patashalaerp.Staff;


public class EmpLeaveModel {

    String empFname, empLname, empUUId, empPhoneNo, empAdhaarNo, empDept;
    String employee_status,wing_name,employee_photo,role,sex;
    String appliedDate;
    // ArrayList<LeaveModel> leaveList;

  /*  public EmpLeaveModel(String appliedDate, ArrayList<LeaveModel> leaveList) {
        this.appliedDate = appliedDate;
        this.leaveList = leaveList;
    }*/

    public EmpLeaveModel(String empFname, String empLname, String empUUId, String empPhoneNo, String empAdhaarNo, String empDept, String employee_status, String wing_name, String employee_photo, String role, String sex) {
        this.empFname = empFname;
        this.empLname = empLname;
        this.empUUId = empUUId;
        this.empPhoneNo = empPhoneNo;
        this.empAdhaarNo = empAdhaarNo;
        this.empDept = empDept;
        this.employee_status = employee_status;
        this.wing_name = wing_name;
        this.employee_photo = employee_photo;
        this.role = role;
        this.sex = sex;
    }

    public String getEmployee_status() {
        return employee_status;
    }

    public void setEmployee_status(String employee_status) {
        this.employee_status = employee_status;
    }

    public String getWing_name() {
        return wing_name;
    }

    public void setWing_name(String wing_name) {
        this.wing_name = wing_name;
    }

    public String getEmployee_photo() {
        return employee_photo;
    }

    public void setEmployee_photo(String employee_photo) {
        this.employee_photo = employee_photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAppliedDate(String appliedDate) {
        this.appliedDate = appliedDate;
    }

    public EmpLeaveModel(String empFname, String empLname, String empUUId, String empPhoneNo, String empAdhaarNo, String empDept) {
        this.empFname = empFname;
        this.empLname = empLname;
        this.empUUId = empUUId;
        this.empPhoneNo = empPhoneNo;
        this.empAdhaarNo = empAdhaarNo;
        this.empDept = empDept;
    }

    public String getEmpFname() {
        return empFname;
    }

    public void setEmpFname(String empFname) {
        this.empFname = empFname;
    }

    public String getEmpLname() {
        return empLname;
    }

    public void setEmpLname(String empLname) {
        this.empLname = empLname;
    }

    public String getEmpUUId() {
        return empUUId;
    }

    public void setEmpUUId(String empUUId) {
        this.empUUId = empUUId;
    }

    public String getEmpPhoneNo() {
        return empPhoneNo;
    }

    public void setEmpPhoneNo(String empPhoneNo) {
        this.empPhoneNo = empPhoneNo;
    }

    public String getEmpAdhaarNo() {
        return empAdhaarNo;
    }

    public void setEmpAdhaarNo(String empAdhaarNo) {
        this.empAdhaarNo = empAdhaarNo;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public String getAppliedDate() {
        return appliedDate;
    }
}
