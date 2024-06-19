class MainMenu extends AbstractMenu {
    private static final MainMenu instance = new MainMenu(null);
    private static final String MAIN_MENU_TEXT =
            "1: 영화 예매하기\n" +
            "2: 영화 확인하기\n" +
            "3: 영화 취소하기\n" +
            "4: 관리자 메뉴로 이동\n" +
            "q: 종료 \n\n" +
            "메뉴를 선택하세요";

    private MainMenu(Menu prevMenu){
        super(MAIN_MENU_TEXT, prevMenu);
    }
    public static MainMenu getInstance(){
        return instance;
    }

    public Menu next(){
        switch (scanner.nextLine()) {
            case "4" :
                if (! checkAdminPassword()){
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    return this;
                }
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.setPrevMenu(this);
                return adminMenu;
            case "q" : return null;
            default: return this;
        }
    }
    private boolean checkAdminPassword() {
        System.out.println("관리자 비밀번호를 입력하세요 : ");
        return "1234".equals(scanner.nextLine());
    }
}
