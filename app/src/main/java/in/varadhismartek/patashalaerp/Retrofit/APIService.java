package in.varadhismartek.patashalaerp.Retrofit;


import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;


/**
 * Created by varadhi on 10/3/18.
 */

public interface APIService {


    // select modules

    @FormUrlEncoded
    @POST("get_school_modules_list")
    Call<Object> getModuleList(
            @Field("school_id") String school_id);


    @FormUrlEncoded
    @POST("add_school_modules")
    Call<Object> addSchoolModules(
            @Field("school_id") String school_id,
            @Field("modules_name") JSONObject modules_name,
            @Field("added_employeeid") String added_employeeid);


    @FormUrlEncoded
    @POST("update_school_modules_status")
    Call<Object> updateModuleStatus(
            @Field("school_id") String school_id,
            @Field("modules_name") JSONObject modules_name,
            @Field("updated_employeeid") String updated_employeeid);


    // wings modules

    @FormUrlEncoded
    @POST("get_school_wings")
    Call<Object> gettingWings(
            @Field("school_id") String school_id

    );

    @FormUrlEncoded
    @POST("add_school_wings")
    Call<Object> addWings(@Field("school_id") String school_id,
                          @Field("wings_names") JSONObject jsonObject,
                          @Field("added_employeeid") String added_employeeid);

    @FormUrlEncoded
    @POST("update_school_wing_name")
    Call<Object> updateSchoolWingName(
            @Field("school_id") String school_id,
            @Field("old_wing_name") String old_wing_name,
            @Field("new_wing_name") String new_wing_name,
            @Field("updated_employeeid") String updated_employeeid

    );


    @FormUrlEncoded
    @POST("update_school_wings_status")
    Call<Object> updateWingsStatus(@Field("school_id") String school_id,
                                   @Field("wings_names") JSONObject jsonObject,
                                   @Field("updated_employeeid") String added_employeeid);

// department module

    @FormUrlEncoded
    @POST("get_school_department")
    Call<Object> gettingDept(
            @Field("school_id") String mobileNumber,
            @Field("wings_name") JSONObject wing_name

    );


    @FormUrlEncoded
    @POST("add_school_department")
    Call<Object> addingDept(
            @Field("school_id") String school_id,
            @Field("wing_name") String wing_name,
            @Field("departments_name") JSONObject departments_name,
            @Field("added_employeeid") String added_employeeid
    );

    @FormUrlEncoded
    @POST("update_school_department_name")
    Call<Object> updateDeptName(
            @Field("school_id") String mobileNumber,
            @Field("wing_name") String wingName,
            @Field("old_department_name") String oldDeptName,
            @Field("new_department_name") String newDeptName,
            @Field("updated_employeeid") String updated_employeeid
    );

    @FormUrlEncoded
    @POST("update_school_departments_status")
    Call<Object> updateDeptStatus(
            @Field("school_id") String school_id,
            @Field("wing_name") String wing_name,
            @Field("departments_name") JSONObject departments_name,
            @Field("updated_employeeid") String added_employeeid
    );


// roles module

    @FormUrlEncoded
    @POST("get_school_roles")
    Call<Object> gettingRoles(
            @Field("school_id") String school_id,
            @Field("wings_name") JSONObject wingName,
            @Field("departments_name") JSONObject deptName);


    @FormUrlEncoded
    @POST("add_school_roles")
    Call<Object> addWingRoles(
            @Field("school_id") String school_id,
            @Field("wing_name") String wingName,
            @Field("department_name") String deptName,
            @Field("roles_name") JSONObject roles,
            @Field("added_employeeid") String employeeid
    );


    @FormUrlEncoded
    @POST("update_school_role_name")
    Call<Object> updateRolesName(
            @Field("school_id") String school_id,
            @Field("wing_name") String wingName,
            @Field("department_name") String deptName,
            @Field("old_role_name") String oldRole,
            @Field("new_role_name") String newRole,
            @Field("updated_employeeid") String updated_employeeid
    );


    @FormUrlEncoded
    @POST("update_school_roles_status")
    Call<Object> updateRolesStatus(
            @Field("school_id") String school_id,
            @Field("wing_name") String wingName,
            @Field("department_name") String deptName,
            @Field("roles_name") JSONObject roles_name,
            @Field("updated_employeeid") String updated_employeeid
    );


//school barrier

    @FormUrlEncoded
    @POST("update_school_student_barrier")
    Call<Object> addStudentBarrier(@Field("school_id") String school_id,
                                   @Field("default_student_admission_no") String default_admission_no,
                                   @Field("minimum_percentage_required") String minimum_percentage_required,
                                   @Field("father_qualification") String father_qualification,
                                   @Field("mother_qualification") String mother_qualification,
                                   @Field("food_facility") String food_facility,
                                   @Field("transport_facility") String transport_facility,
                                   @Field("no_of_guardians") String no_of_guardians,
                                   @Field("updated_employee_id") String updated_employee_id
    );

    @FormUrlEncoded
    @POST("get_school_student_barrier")
    Call<Object> getstatusStudentBarriers(
            @Field("school_id") String get_school_id);


//staff barriers

    @FormUrlEncoded
    @POST("get_school_staff_barrier")
    Call<Object> getStaffBarriers(
            @Field("school_id") String get_school_id);

    @FormUrlEncoded
    @POST("add_school_staff_barrier")
    Call<Object> addStaffBarrier(
            @Field("school_id") String schoolId,
            @Field("default_teacher_admission_no") String default_teacher_admission_no,
            @Field("added_by_employeeid") String added_by_employeeid);


    @FormUrlEncoded
    @POST("update_school_staff_barrier")
    Call<Object> updateStaffBarriers(
            @Field("school_id") String get_school_id,
            @Field("updated_teacher_admission_no") String updated_teacher_admission_no,
            @Field("updated_by_employeeid") String updated_by_employeeid);

    //Division

    @FormUrlEncoded
    @POST("get_divisions")
    Call<Object> getDivisions(
            @Field("school_id") String school_id);

    @FormUrlEncoded
    @POST("add_division")
    Call<Object> addDivision(
            @Field("school_id") String school_id,
            @Field("data") String data,
            @Field("employee_uuid") String employee_uuid);

    @FormUrlEncoded
    @POST("update_division_name")
    Call<Object> updateDivisionName(
            @Field("school_id") String school_id,
            @Field("division_old_name") String old_name,
            @Field("division_new_name") String new_name,
            @Field("employee_uuid") String employee_uuid
    );

    @FormUrlEncoded
    @POST("update_division_status")
    Call<Object> updateDivisionStatus(
            @Field("school_id") String school_id,
            @Field("divisions") JSONArray divisions,
            @Field("employee_uuid") String employee_uuid);


//maker checker


    @FormUrlEncoded
    @POST("add_school_maker_and_checker")
    Call<Object> addMakerChecker(
            @Field("school_id") String school_id,
            @Field("module_name") String module_name,
            @Field("added_employeeid") String added_employeeid,
            @Field("makers") JSONObject maker,
            @Field("checkers") JSONObject checkers
    );

    @FormUrlEncoded
    @POST("get_school_maker_and_checker")
    Call<Object> getMakerChecker(
            @Field("school_id") String school_id
    );


    @FormUrlEncoded
    @POST("delete_school_maker_and_checker")
    Call<Object> deleteMakerChecker(
            @Field("school_id") String school_id,
            @Field("module_name") String module_name,
            @Field("deleting_employeeid") String deleting_employeeid
    );

    //Class Module


    @FormUrlEncoded
    @POST("get_school_classes_sections")
    Call<Object> getClassSections(
            @Field("school_id") String school_id,
            @Field("divisions") JSONObject division
    );


    @FormUrlEncoded
    @POST("add_classes_sections")
    Call<Object> addClassSections(
            @Field("school_id") String school_id,
            @Field("division_name") String division,
            @Field("classes_sections") JSONObject classSection,
            @Field("added_employeeid") String employeeID
    );


    @FormUrlEncoded
    @POST("del_classes_sections")
    Call<Object> deleteClassSections(
            @Field("school_id") String school_id,
            @Field("classes_sections") JSONObject classSection,
            @Field("deleting_employeeid") String employeeID
    );

    //sessions
    @FormUrlEncoded
    @POST("add_school_sessions")
    Call<Object> addSession(
            @Field("school_id") String school_id,
            @Field("academic_start_date") String fdate,
            @Field("academic_end_date") String todate,
            @Field("weekly_working_days") String workingday,
            @Field("sessions") JSONObject sessions,
            @Field("added_employeeid") String empID
    );

    @FormUrlEncoded
    @POST("get_school_academic_years")
    Call<Object> getAcademicYear(
            @Field("school_id") String school_id

    );

    @FormUrlEncoded
    @POST("get_school_sessions")
    Call<Object> getSessions(
            @Field("school_id") String school_id,
            @Field("academic_start_date") String startDate,
            @Field("academic_end_date") String toDate
    );


    @FormUrlEncoded
    @POST("delete_school_sessions")
    Call<Object> deleteSessions(
            @Field("school_id") String school_id,
            @Field("academic_start_date") String startDate,
            @Field("academic_end_date") String endDate,
            @Field("session_serial_no") String serialNo,
            @Field("added_employeeid") String empId,
            @Field("session_from_date") String fromdate,
            @Field("session_to_date") String todate
    );

    @FormUrlEncoded
    @POST("update_school_sessions")
    Call<Object> upDateSession(
            @Field("school_id") String school_id,
            @Field("academic_start_date") String startDate,
            @Field("academic_end_date") String endDate,
            @Field("weekly_working_days") String workday,
            @Field("added_employeeid") String empId,
            @Field("session_from_date") String fromdate,
            @Field("session_to_date") String todate
    );

    //Assessment -> Grade


    @FormUrlEncoded
    @POST("add_grade_barrier")
    Call<Object> addGradeBarrier(
            @Field("school_id") String school_id,
            @Field("grade_data") JSONObject grade_data,
            @Field("employee_uuid") String employee_uuid,
            @Field("academic_start_date") String academic_start_date
    );


    @FormUrlEncoded
    @POST("get-status-grade-barrier")
    Call<Object> getGradeBarrier(
            @Field("school_id") String school_id);

    /*Homework*/

    @FormUrlEncoded
    @POST("get_homework_barrier")
    Call<Object> getHomeworkBarrier(
            @Field("school_id") String school_id
    );

    @FormUrlEncoded
    @POST("add_homework_barrier")
    Call<Object> addHomeworkBarrier(
            @Field("school_id") String school_id,
            @Field("division") String division,
            @Field("homework_status") String homework_status,
            @Field("no_of_homeworks") String no_of_homeworks,
            @Field("custom_employee_id") String custom_employee_id
    );

    @FormUrlEncoded
    @POST("update_homework_barrier")
    Call<Object> updateHomeworkBarrier(
            @Field("school_id") String school_id,
            @Field("division") String division,
            @Field("no_of_homeworks") String no_of_homeworks,
            @Field("custom_employee_id") String custom_employee_id
    );

    @FormUrlEncoded
    @POST("update_school_homework_barrier_status")
    Call<Object> updateSchoolHomeworkBarrierStatus(
            @Field("school_id") String school_id,
            @Field("barrier_status") String barrier_status,
            @Field("employee_uuid") String employee_uuid
    );

    @FormUrlEncoded
    @POST("update_divisions_homework_barrier_status")
    Call<Object> updateDivisionsHomeworkBarrierStatus(
            @Field("school_id") String school_id,
            @Field("custom_employee_id") String custom_employee_id,
            @Field("data") String data
    );

    //Exam type Module

    @FormUrlEncoded
    @POST("get_exam_type")
    Call<Object> getExamList(
            @Field("school_id") String school_id
    );


    @FormUrlEncoded
    @POST("add_exam_type")
    Call<Object> addExamType(
            @Field("school_id") String school_id,
            @Field("exam_type") JSONArray exam_type,
            @Field("employee_uuid") String empId
    );


    @FormUrlEncoded
    @POST("update_exam_type_name")
    Call<Object> updateExameByname(
            @Field("school_id") String school_id,
            @Field("old_name") String old_name,
            @Field("new_name") String new_name,
            @Field("employee_uuid") String empId
    );


    @FormUrlEncoded
    @POST("update_exam_type_status")
    Call<Object> updateExamByStatus(
            @Field("school_id") String school_id,
            @Field("exam_type") JSONObject exam_type,
            @Field("employee_uuid") String empId
    );

    //exam barriers


    @FormUrlEncoded
    @POST("get-status-exam-barrier")
    Call<Object> getExamBarriers(
            @Field("school_id") String school_id,
            @Field("exam_type") String exam_type,
            @Field("division") String division,
            @Field("class") String classType
    );


    @FormUrlEncoded
    @POST("add_exam_barrier")
    Call<Object> addExamBarrier(
            @Field("school_id") String school_id,
            @Field("exam_type") String exam_type,
            @Field("division") String division,
            @Field("class") String classType,
            @Field("subject") String subject,
            @Field("min_marks") long min_marks,
            @Field("max_marks") long max_marks,
            @Field("exam_duration") String exam_duration,
            @Field("added_by_uuid") String added_by_uuid

    );

// Add subject



    @FormUrlEncoded
    @POST("get_subjects")
    Call<Object> getSubject(
            @Field("school_id") String school_id,
            @Field("division") String division,
            @Field("class") String classType
    );



    @FormUrlEncoded
    @POST("add_subjects")
    Call<Object> addSubject(
            @Field("school_id") String school_id,
            @Field("division") String division,
            @Field("class") String classType,
            @Field("data") JSONObject data,
            @Field("employee_uuid") String employee_uuid

    );

    @FormUrlEncoded
    @POST("add_reminder")
    Call<Object> addReminer(
            @Field("school_id") String school_id,
            @Field("added_employeeid") String division,
            @Field("academic_start_date") String classType,
            @Field("session_serial_no") String session_serial_no,
            @Field("fee_type") String fee_type,
            @Field("reminder") JSONObject reminder

    );
    @FormUrlEncoded
    @POST("get-status-reminder")
    Call<Object> getReminer(
            @Field("school_id") String school_id
    );

    /*school_id:ee99d168-cd9d-4f22-9632-4fb36f8e5747
added_employeeid:d299d357-291a-4466-aef4-3386f98f7ac4
academic_start_date:2019-01-01
session_serial_no:1
fee_type:Exam
reminder:{'1':{'reminder_no':'1','no_of_days':'2','from_time':'1:55 pm','to_time':'2:55 pm','reminder_text':'hey hello how are you had dinner'}}*/


    @FormUrlEncoded
    @POST("update_subject")
    Call<Object> updateSubject(
            @Field("school_id") String school_id,
            @Field("division") String division,
            @Field("employee_uuid") String employee_uuid,
            @Field("class") String classType,
            @Field("sections") JSONArray data,
            @Field("old_subject") String old_subject,
            @Field("new_subject") String new_subject,
            @Field("new_subject_code") String new_subject_code,
            @Field("new_subject_type") String new_subject_type,
            @Field("new_status") String new_status

    );
    //Curricular


    @FormUrlEncoded
    @POST("get-status-co-curricular-activities")
    Call<Object> getCurricular(
            @Field("school_id") String school_id
    );


    @FormUrlEncoded
    @POST("add_co_curricular_activities")
    Call<Object> addCurricular(
            @Field("school_id") String school_id,
            @Field("data") JSONObject data,
            @Field("added_employeeid") String added_employeeid
    );

    @FormUrlEncoded
    @POST("update_co_curricular_activities")
    Call<Object> updateCurricular(
            @Field("school_id") String school_id,
            @Field("added_employeeid") String added_employeeid,
            @Field("old_activity_name") String old_activity_name,
            @Field("new_activity_name") String new_activity_name,
            @Field("new_marks") String new_marks,
            @Field("new_status") String new_status
    );

    //****************************************sports

    @FormUrlEncoded
    @POST("get_sports")
    Call<Object>getSports(@Field("school_id") String school_id);

    @FormUrlEncoded
    @POST("add_sports")
    Call<Object>addSports(
            @Field("school_id") String school_id,
            @Field("sports") JSONArray sports,
            @Field("added_employeeid") String added_employeeid
    );

    @FormUrlEncoded
    @POST("update_sports_name")
    Call<Object>updateSportsByName(
            @Field("school_id") String school_id,
            @Field("added_employeeid") String empid,
            @Field("old_sports_name") String old_sports_name,
            @Field("new_sports_name") String new_sports_name
    );

    @FormUrlEncoded
    @POST("update_sports_status")
    Call<Object>updateSportsByStatus(
            @Field("school_id") String school_id,
            @Field("data") JSONObject datasports,
            @Field("added_employeeid") String added_employeeid
    );

    @FormUrlEncoded
    @POST("get_sports_barrier")
    Call<Object>getSportBarriers(
            @Field("school_id") String school_id
    );

    @FormUrlEncoded
    @POST("update_sports_barrier")
    Call<Object>updateAddSportBarriers(
            @Field("school_id") String school_id,
            @Field("added_employeeid") String added_employeeid,
            @Field("data") JSONObject data
    );

    //House module


    @FormUrlEncoded
    @POST("get_school_houses")
    Call<Object>getHouseList(
            @Field("school_id") String school_id
    );

    /*****************************************Homework Module********************************/

    @FormUrlEncoded
    @POST("get_school_homework_list")
    Call<Object>getHomeWorkBySchool(
            @Field("school_id") String school_id
    );

    @FormUrlEncoded
    @POST("get_class_homework_list")
    Call<Object>getHomeWorkByClass(
            @Field("school_id") String school_id,
            @Field("classes") String classes
    );

    @FormUrlEncoded
    @POST("create_homework")
    Call<Object>createHomework(
            @Field("school_id") String school_id,
            @Field("class") String classes,
            @Field("subject") String subject,
            @Field("homework_title") String homework_title,
            @Field("start_date") String start_date,
            @Field("end_date") String end_date,
            @Field("description") String description,
            @Field("sections") String sections,
            @Field("added_employeeid") String added_employeeid,
            @Field("book_name") JSONObject book_name,
            @Field("reference_url") JSONObject reference_url,
            @Field("refrence_attachments") JSONObject refrence_attachments
    );

    @FormUrlEncoded
    @POST("get_homework_details")
    Call<Object> getHomwWorkDetails(
            @Field("school_id") String school_id,
            @Field("homework_uuid") String homework_uuid

    );



    /*************************************************Teacher Module Homework list*****************/
   // http://13.233.109.165:8000/school/get_school_homework_report_list
    //http://13.233.109.165:8000/school/get_class_homework_report_list
    //http://13.233.109.165:8000/school/get_student_homework_list

    @FormUrlEncoded
    @POST("get_school_homework_report_list")
    Call<Object> getHomeworkListTeacher(
            @Field("school_id") String school_id
    );


    @FormUrlEncoded
    @POST("get_class_homework_report_list")
    Call<Object> getHomeworkListTeacherClass(
            @Field("school_id") String school_id,
            @Field("classes") String classes

    );



    @FormUrlEncoded
    @POST("get_student_homework_list")
    Call<Object> HomeworkListByClassSection(
            @Field("school_id") String school_id,
            @Field("classes") String classes,
            @Field("section") String section

    );

    /***************************************Student module**********************************/


    // http://13.233.109.165:8000/school/get_student_homework_report_list
    //http://13.233.109.165:8000/school/submit_student_homework

    @FormUrlEncoded
    @POST("get_student_homework_report_list")
    Call<Object> getHomeworkStudent(
            @Field("school_id") String school_id,
            @Field("classes") String classes,
            @Field("section") String section,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("dob") String dob,
            @Field("birth_certificate_number") String birthCertificateno,
            @Field("student_uuid") String student_uuid

    );



    @FormUrlEncoded
    @POST("submit_student_homework")
    Call<Object>submitStudentHomework(
            @Field("school_id") String school_id,
            @Field("classes") String classes,
            @Field("section") String section,
            @Field("homework_title") String homework_title,
            @Field("subject") String subject,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("dob") String dob,
            @Field("birth_certificate_number") String birth_certificate_number,
            @Field("completion_percent") String completion_percent,
            @Field("description") String description,
            @Field("student_uuid") String student_uuid,
            @Field("homework_uuid") String homework_uuid

    );

/*Gallery module*/

    @FormUrlEncoded
    @POST("getting_school_gallery")
    Call<Object>getGallery(
            @Field("school_id") String school_id
    );


    @Multipart
    @POST("creating_school_gallery")
    Call<Object>createGallery(
     @Part("school_id") RequestBody school_id,
            @Part("employee_id") RequestBody employee_id,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("phone_no") RequestBody phone_no,
            @Part("adhaar_card_no") RequestBody adhaar_card_no,
            @Part("description") RequestBody description,
            @Part("title") RequestBody title,
           // @Part MultipartBody.Part  );

            @Part("gallery_image") RequestBody gallery_image);




    @FormUrlEncoded
    @POST("delete_school_gallery")
    Call<Object>deleteGallery(
            @Field("school_id") String school_id,
            @Field("employee_id") String employee_id,
            @Field("gallery_id") String gallery_id,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("phone_no") String phone_no,
            @Field("adhaar_card_no") String adhaar_card_no
    );

    /*****************************************Question Bank*****************************/

    @FormUrlEncoded
    @POST("get_school_question_bank")
    Call<Object>getQuestionBank(
            @Field("school_id") String school_id
    );

    @FormUrlEncoded
    @POST("get_class_question_bank")
    Call<Object>getQuestionBankBysubject(
            @Field("school_id") String school_id,
            @Field("classes") String classes,
            @Field("sections") String sections,
            @Field("subject") String subject
    );

    @FormUrlEncoded
    @POST("delete_question_bank")
    Call<Object>deleteQuestionBank(
            @Field("school_id") String school_id,
            @Field("question_bank_id") String questionbankId
    );



    @Multipart
    @POST("add_question_bank")
    Call<Object> addQuestionBank(

            @Part("school_id") RequestBody school_id,
            @Part("class") RequestBody classes,
            @Part("sections") RequestBody sections,
            @Part("subject") RequestBody subject,
            @Part("added_employeeid") RequestBody added_employeeid,
            @Part("question_bank_title") RequestBody question_bank_title,
            @Part("description") RequestBody description,
           // @Part MultipartBody.Part file
            @Part("question_bank_attachement") RequestBody questionBankImage

    );
// fees
    @FormUrlEncoded
    @POST("create_fee_structure")
    Call<Object>createFeeStructure(
            @Field("school_id") String  school_id,
            @Field("added_employeeid") String added_employeeid,
            @Field("academic_start_date") String academic_start_date,
            @Field("data") JSONObject feeData
    );

    @FormUrlEncoded
    @POST("get-status-fee-structure")
    Call<Object>getFeeStructure(
            @Field("school_id") String  school_id,
            @Field("academic_start_date") String academic_start_date
    );

    @FormUrlEncoded
    @POST("update_fee_structure")
    Call<Object>updateFeeStructure(
            @Field("school_id") String  school_id,
            @Field("academic_start_date") String academic_start_date,
            @Field("added_employeeid") String added_employeeid,
            @Field("new_status") String new_status,
            @Field("session_serial_no") String session_serial_no,
            @Field("old_fee_type") String old_fee_type,
            @Field("new_fee_type") String new_fee_type,
            @Field("new_installment") String new_installment,
            @Field("new_due_date") String new_due_date,
            @Field("new_fee_code") String new_fee_code
    );


    // emp list
    @FormUrlEncoded
    @POST("get_school_all_employee_lists")
    Call<Object> getAllEmployeeList(
            @Field("school_id") String school_id
    );

    @FormUrlEncoded
    @POST("getting_employee_personal_details")
    Call<Object> getEmpPersonalData(
            @Field("school_id") String school_id,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("adhaar_card_no") String adhaar_card_no
    );

}
