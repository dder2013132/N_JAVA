package ui;

import dao.DBConnection;
import dao.MemberDAO;

import model.Member;
import util.ConsoleUtil;
import util.ValidationUtil;

/**
 * 로그인 및 회원가입 UI 클래스
 */
public class LoginUI {
    private MemberDAO memberDAO;
    int uiwidth = 80;
    public LoginUI() {
        this.memberDAO = new MemberDAO();
    }
    
    
    
    /**
     * 로그인 화면을 표시하고 처리하는 메소드
     * @return 로그인 성공 시 Member 객체, 실패 시 null
     */
    public Member showLoginUI() {
        Member member = null;
        
        while (true) {
            ConsoleUtil.clearScreen();
            ConsoleUtil.printHeader("Game Shop & Review");
            ConsoleUtil.printMessage(ConsoleUtil.YELLOW + ConsoleUtil.centerText("환영합니다! 로그인하거나 회원가입해주세요.", uiwidth + 10)  + ConsoleUtil.RESET);
            ConsoleUtil.printDivider();
            ConsoleUtil.printMessage("1. " + ConsoleUtil.GREEN + "로그인" + ConsoleUtil.RESET);
            ConsoleUtil.printMessage("2. " + ConsoleUtil.BLUE + "회원가입" + ConsoleUtil.RESET);
            ConsoleUtil.printMessage("3. " + ConsoleUtil.RED + "종료" + ConsoleUtil.RESET);
            ConsoleUtil.printDivider();
            
            int choice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
            
            switch (choice) {
                case 1:
                    member = processLogin();
                    if (member != null) {
                        return member; // 로그인 성공
                    }
                    break;
                case 2:
                    boolean registered = processRegistration();
                    if (registered) {
                        ConsoleUtil.printSuccess("회원가입이 완료되었습니다. 로그인 화면으로 돌아갑니다.");
                        ConsoleUtil.pressEnterToContinue();
                    }
                    break;
                case 3:
                    DBConnection.exitApplication(); // 프로그램 종료
                    break;
                default:
                    ConsoleUtil.printError("잘못된 선택입니다. 다시 선택해주세요.");
                    ConsoleUtil.pressEnterToContinue();
            }
        }
    }
    
    /**
     * 로그인 프로세스를 처리하는 메소드
     * @return 로그인 성공 시 Member 객체, 실패 시 null
     */
    private Member processLogin() {
        ConsoleUtil.printDivider();
        String id = ConsoleUtil.readString("ID >> ");
        String password = ConsoleUtil.readString("PWD >> ");
        
        Member member = memberDAO.login(id, password);
        
        if (member != null) {
            ConsoleUtil.printMessage(member.getUserName() + " 님, 환영합니다.");
            return member;
        } else {
            ConsoleUtil.printError("아이디 또는 비밀번호가 일치하지 않습니다.");
            return null;
        }
    }
    
    /**
     * 회원가입 프로세스를 처리하는 메소드
     * @return 회원가입 성공 여부
     */
    private boolean processRegistration() {
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("=====================================================");
        
        // ID 입력 및 중복 확인
        String id;
        while (true) {
            id = ConsoleUtil.readString("ID >> ");
            
            if (!ValidationUtil.isValidId(id)) {
                ConsoleUtil.printError("아이디는 영문자와 숫자로 4~20자로 입력해주세요.");
                continue;
            }
            
            if (memberDAO.isIdDuplicated(id)) {
                ConsoleUtil.printError("이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.");
                continue;
            }
            
            break;
        }
        
        // 비밀번호 입력
        String password;
        while (true) {
            password = ConsoleUtil.readString("PWD >> ");
            
            if (!ValidationUtil.isValidPassword(password)) {
                ConsoleUtil.printError("비밀번호는 최소 4자 이상이어야 합니다.");
                continue;
            }
            
            break;
        }
        
        // 이름 입력
        String name;
        while (true) {
            name = ConsoleUtil.readString("이름 >> ");
            
            if (ValidationUtil.isEmpty(name)) {
                ConsoleUtil.printError("이름을 입력해주세요.");
                continue;
            }
            
            break;
        }
        
        // 이메일 입력 및 중복 확인
        String email;
        while (true) {
            email = ConsoleUtil.readString("이메일 >> ");
            
            if (!ValidationUtil.isValidEmail(email)) {
                ConsoleUtil.printError("올바른 이메일 형식이 아닙니다.");
                continue;
            }
            
            if (memberDAO.isEmailDuplicated(email)) {
                ConsoleUtil.printError("이미 사용 중인 이메일입니다. 다른 이메일을 입력해주세요.");
                continue;
            }
            
            break;
        }
        
        // 전화번호 입력
        String phone;
        while (true) {
            phone = ConsoleUtil.readString("전화번호 >> ");
            
            if (!ValidationUtil.isValidPhone(phone)) {
                ConsoleUtil.printError("올바른 전화번호 형식이 아닙니다. (예: 010-1234-5678)");
                continue;
            }
            
            break;
        }
        
        // 주소 입력
        String address = ConsoleUtil.readString("주소 >> ");
        
        ConsoleUtil.printMessage("=====================================================");
        
        // Member 객체 생성 및 등록
        Member member = new Member(id, password, name, email, phone, address);
        boolean success = memberDAO.register(member);
        
        return success;
    }
}