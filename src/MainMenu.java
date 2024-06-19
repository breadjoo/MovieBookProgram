class MainMenu extends AbstractMenu {
    private static final MainMenu instance = new MainMenu(null);
    private static final String MAIN_MENU_TEXT =
            "1: 영화 예매하기\n" +
            "2: 영화 확인하기\n" +
            "3: 영화 취소하기\n" +
            "4: 관리자 메뉴로 이동\n" +
            "5: 종료 \n\n" +
            "메뉴를 선택하세요";

    private MainMenu(Menu prevMenu){
        super(MAIN_MENU_TEXT, prevMenu);
    }
    public static MainMenu getInstance(){
        return instance;
    }
}
