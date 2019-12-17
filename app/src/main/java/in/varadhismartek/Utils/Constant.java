package in.varadhismartek.Utils;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

import in.varadhismartek.patashalaerp.AddDepartment.Data;
import in.varadhismartek.patashalaerp.AddWing.AddWingsModel;
import in.varadhismartek.patashalaerp.DivisionModule.ClassSectionAndDivisionModel;

/**
 * Created by varadhi on 22/8/18.
 */

public class Constant {


    public static String audience_type;
    public static String staffMobNumber;
    public static StringBuffer maskingNumber;
    public static final String IMAGE_LINK = "https://patashalafinder.in/media/";

    public static String staffRegNumber;
    public static Drawable audienceType;
    public static final String FIRST_CHOOSE_AUDIENCE_FRAGMENT = "FIRST_CHOOSE_AUDIENCE_FRAGMENT";
    public static final String SECOND_SEARCH_USER_FRAGMENT = "ENTER_MOBILE_NUMBER_FRAGMENT";
    public static final String THIRD_OTP_ENTER_FRAGMENT = "THIRD_OTP_ENTER_FRAGMENT";
    public static final String FOURTH_MPIN_FRAGMENT = "FOURTH_MPIN_FRAGMENT";
    public static String FORGET_RESET_MPIN = "";


    public static String MPIN = "";
    public static String SCHOOL_ID = "";
    public static String POC_NAME = "";
    public static String CLASS = "";
    public static String MOBILENO = "";
    public static String MANAGEMENT_ID = "";
    public static ArrayList<String> CHECKED_ARRAYLIST;


    public static final String CHECKER_ADAPTER = "CHECKER_ADAPTER";
    public static final String MAKER_ADAPTER = "MAKER_ADAPTER";
    public static final String BARRIERS_FRAG = "BARRIERS_FRAG";
    public static final String SELECTED_FRAG = "SELECTED_FRAG";
    public static final String STATUS = "status";
    public static final String DEPARTMENT_FRAG = "DEPARTMENT_FRAG";
    public static final String UPDATE = "UPDATE";
    public static final String _NOT_UPDATE = "_NOT_UPDATE";
    public static ArrayList<String> DIVISION_ARRAY_LIST = new ArrayList<>();
    public static ArrayList<String> CLASSES_ARRAY_LIST = new ArrayList<>();


    public static String STAFF_TYPE;
    public static ArrayList<Data> employeeRolesArrayList;

    public static String mobile_otp;
    public static String email_otp;
    public static String mobile_message;
    public static String mobile_number;
    public static String ACTIVITY_STATUS = "false";

    public static final String SUCCESS = "Success";
    public static final String MOBILE_DOESNOT_EXIST = "Mobile number does not exist";

    public static ArrayList<AddWingsModel> wingsArrayList;

    public static String poc_mob = "";
    public static String wingName = "";
    public static String Division = "";
    public static String deptName = "";
    public static String schoolid = "";

    /*login response*/
    public static String Organisation_name = "";
    public static String POC_Email = "";
    public static String POC_Image = "";
    public static String POC_Mobile_Number = "";
    public static String EMPLOYEE_BY_ID = "";
    public static String DEPART_NAME = "";
    public static String DIVISION_NAME = "";
    public static String ROLES_NAME = "";
    public static String PAGE_ID = "";


    public static final String RV_MAKER_TYPE = "RV_MAKER_TYPE";
    public static final String RV_CHECKER_TYPE = "RV_CHECKER_TYPE";

    public static final String MAKER_CHECKER_FRAGMENT = "MAKER_CHECKER_FRAGMENT";
    public static final String ADD_DIVISION_FRAGMENT = "ADD_DIVISION_FRAGMENT";
    public static final String RV_DIVISION_CARD = "RV_DIVISION_CARD";
    public static final String ADD_CLASSES_FRAGMENT = "ADD_CLASSES_FRAGMENT";
    public static final String ADD_SECTION_FRAGMENT = "ADD_SECTION_FRAGMENT";
    public static final String ADD_SESSION_FRAGMENT = "ADD_SESSION_FRAGMENT";

    public static final String RV_CLASSES_CARD = "RV_CLASSES_CARD";
    public static final String RV_SESSION_ROW = "RV_SESSION_ROW";
    public static final String RV_SECTION_ROW = "RV_SECTION_ROW";
    public static final String RV_ASSESSMENT_GRADE_ROW = "RV_ASSESSMENT_GRADE_ROW";
    public static final String RV_SESSION_ROW_NEW = "RV_SESSION_ROW_NEW";

    public static String ACTIVE_PAGE = "0";

    /*Homework*/
    public static final String HOMEWORK_HOME_FRAGMENT = "HOMEWORK_HOME_FRAGMENT";
    public static final String HOMEWORK_BARRIER_FRAGMENT = "HOMEWORK_BARRIER_FRAGMENT";
    public static final String HOMEWORK_INBOX_FRAGMENT = "HOMEWORK_INBOX_FRAGMENT";
    public static final String HOMEWORK_CREATE_FRAGMENT = "HOMEWORK_CREATE_FRAGMENT";
    public static final String HOMEWORK_VIEW_FRAGMENT = "HOMEWORK_VIEW_FRAGMENT";

    public static final String RV_HOMEWORK_BARRIER = "RV_HOMEWORK_BARRIER";
    public static final String RV_HOMEWORK_BOOK = "RV_HOMEWORK_BOOK";
    public static final String RV_HOMEWORK_URL = "RV_HOMEWORK_URL";
    public static final String RV_HOMEWORK_INBOX_ROW = "RV_HOMEWORK_INBOX_ROW";
    public static final String RV_HOMEWORK_VIEW_ATTACHMENT = "RV_HOMEWORK_VIEW_ATTACHMENT";


    /* ASSESSMENT*/
    public static final String ASSESSMENT_GRADE = "ASSESSMENT_GRADE";
    public static final String ADD_EXAMINATION = "ADD_EXAMINATION";
    public static final String ADD_EXAM_BARRIER = "ADD_EXAM_BARRIER";
    public static final String ADD_CURRICULAR = "ADD_CURRICULAR";
    public static final String ADD_SUBJECT = "ADD_SUBJECT";
    public static final String ADD_SPORT = "ADD_SPORT";
    public static final String ADD_SPORT_BARRIER = "ADD_SPORT_BARRIER";
    public static final String ADD_HOUSES = "ADD_HOUSES";

    public static final String RV_EXAMS_ROW = "RV_EXAMS_ROW";
    public static final String RV_EXAMS_BARRIER_ROW = "RV_EXAMS_BARRIER_ROW";
    public static final String RV_SUBJECT_ROW = "RV_SUBJECT_ROW";
    public static final String RV_CURRICULAR_ROW = "RV_CURRICULAR_ROW";
    public static final String RV_ASSESSMENT_SPORTS_ROW = "RV_ASSESSMENT_SPORTS_ROW";
    public static final String RV_ASSESSMENT_SPORTS_BARRIER = "RV_ASSESSMENT_SPORTS_BARRIER";
    public static final String RV_ADD_HOUSES = "RV_ADD_HOUSES";

    public static ArrayList<String> listDivisionNew;

    /*student*/
    public static final String STU_CREATE_HOMEWORK = "STU_CREATE_HOMEWORK";

    /*Teacher*/
    public static final String TEACHER_HOMEWORK_LIST = "TEACHER_HOMEWORK_LIST";
    public static final String RV_TEACHER_HOMEWORK_ROW = "RV_TEACHER_HOMEWORK_ROW";
    public static final String RV_STUDENT_HOMEWORK_ROW = "RV_STUDENT_HOMEWORK_ROW";
    public static final String STUDENT_HOMEWORK_VIEW = "STUDENT_HOMEWORK_VIEW";
    public static final String GALLERY_TITLE = "GALLERY_TITLE";
    public static final String RV_NESTED_GALLERY_IMAGE = "RV_NESTED_GALLERY_IMAGE";


    public static final String GALLERY_LANDING_FRAGMENT = "GALLERY_LANDING_FRAGMENT";
    public static final String FRAGMENT_SELECT_GALLERY_IMAGES = "FRAGMENT_SELECT_GALLERY_IMAGES";
    public static final String SCHEDULE_FRAGMENT = "SCHEDULE_FRAGMENT";

    public static final String RV_GALLERY_IMAGES = "RV_GALLERY_IMAGES";
    public static final String RV_DIALOG_IMAGE_GALLERY = "RV_DIALOG_IMAGE_GALLERY";

    public static final String QUESTIONBANK_LIST = "QUESTIONBANK_LIST";
    public static final String QUESTIONBANK__CREATE = "QUESTIONBANK__CREATE";
    public static final String RV_QUESTION_BANK_ROW = "RV_QUESTION_BANK_ROW";
    public static final String RV_SUBJECT_ROW_CLASS = "RV_SUBJECT_ROW_CLASS";


}











/*
    public static ArrayList<String> WINGS_ARRAY_LIST = new ArrayList<>();
    public static ArrayList<String> DEPARTMENY_ARRAY_LIST = new ArrayList<>();
    public static ArrayList<String> ROLES_ARRAY_LIST = new ArrayList<>();
    public static final String FRAGMENT_DASHBOARD = "FRAGMENT_DASHBOARD";
    public static Boolean Activity_Status = false;
    public static ArrayList<String> MODULE_ARRAY_LIST = new ArrayList<>();
    public static final String RV_MAKER_CHECKER = "RV_MAKER_CHECKER";
    public static final String ADD_MAKER = "ADD_MAKER";
    public static final String ADD_CHECKER = "ADD_CHECKER";
    public static String MODULE_NAME = "";

    public static ArrayList<ClassSectionAndDivisionModel> ARRAY_LIST_FOR_CLASSES = new ArrayList<>();
    public static final String DEPARTMENT_FRAGMENT = "DEPARTMENT_FRAGMENT";*/
