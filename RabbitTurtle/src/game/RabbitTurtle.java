package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class RabbitTurtle {
	public static int Mcount=0; //움직인 횟수
	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();
		JLabel MoveCount = new JLabel("0",JLabel.CENTER); //플레이어의 이동 횟수
        final ImageIcon successIcon = new ImageIcon("successIcon.png"); //게임 클리어 시 이미지
		final ImageIcon backGround = new ImageIcon("backGround.png"); //배경 이미지
		final ImageIcon rab = new ImageIcon("rab.png"); //토끼(몬스터)
		final ImageIcon turD = new ImageIcon("turD.png"); //플레이어(거북이(아래방향))
		final ImageIcon turL = new ImageIcon("turL.png"); //플레이어(거북이(왼쪽방향))
		final ImageIcon turR = new ImageIcon("turR.png"); //플레이어(거북이(오른쪽방향))
		final ImageIcon turU = new ImageIcon("turU.png"); //플레이어(거북이(위쪽방향))
        
        final JButton button = new JButton(successIcon); // 성공 시
		final CardLayout card = new CardLayout(); //
		final JDialog dialog = new JDialog(); // 게임 클리어 화면

		random = new Random(); //몬스터인 토끼는 랜덤 방향으로 이동한다. 랜덤 객체 생성

		dialog.setSize(650,650); //클리어 화면
		dialog.setVisible(false); //클리어 시 나와야 하기 때문에 안 보이도록 처리한다.

		turH=12;  turW=7;  rabH=7;  rabW=7;  start=2;  temp=backGround;
        // 기본 시작 좌표


		final JLabel[][] f = new JLabel[13][13]; //게임 화면은 13x13 좌표를 활용한다.

		for (int i=0; i<13; i++) {
			for(int j=0; j<13; j++) {
				f[i][j] = new JLabel(); //게임 화면의 좌표인 레이블은 이렇게 이루어진다.
			}
		}

		class Blistener implements ActionListener { //게임 종료 메소드
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
            }
		}


		class TListener implements ActionListener {   //timer?????? ActionListener
			public void actionPerformed(ActionEvent event) //토끼(몬스터)의 이동 이벤트 처리 메소드
			{
				if(start<=0) where = 1+random.nextInt(4); //1에서 4 사이의 랜덤 수 발생
				else { where = 1; start--; }
					switch(where) { //where의 값에 따라 아래 케이스로 분기
						case 1:
								temp1=f[rabH-1][rabW].getIcon(); //이동'할' 좌표의 아이콘을 가져와서
								f[rabH-1][rabW].setIcon(rab); //이동'할' 좌표에 토끼(몬스터)의 아이콘을 삽입
								f[rabH][rabW].setIcon(temp); //기존 좌표는 빈 공간으로 처리
								temp=temp1; //기존 빈 공간은 새로운 빈 공간으로 처리
								rabH--; //토끼의 높이 좌표값을 1 감소
							break;
						case 2: //이후 다른 번호에 대해서도 같은 방법을 해 준다.
								temp2=f[rabH+1][rabW].getIcon();
								f[rabH+1][rabW].setIcon(rab);
								f[rabH][rabW].setIcon(temp);
								temp=temp2;
								rabH++;
							break;
						case 3:
								temp3=f[rabH][rabW-1].getIcon();
								f[rabH][rabW-1].setIcon(rab);
								f[rabH][rabW].setIcon(temp);
								temp=temp3;
								rabW--;
							break;
						case 4:
								temp4=f[rabH][rabW+1].getIcon();
								f[rabH][rabW+1].setIcon(rab);
								f[rabH][rabW].setIcon(temp);
								temp=temp4;
								rabW++;
							break;
					}
					if(rabH==turH && rabW==turW) { //승리 조건
						f[rabH][rabW].setIcon(rab);
						dialog.add(button); //다이얼로그에 버튼 추가
						dialog.setVisible(true); //승리 조건 다이얼로그
					}

				if(rabH==5 && rabW==7) { f[6][7].setIcon(backGround); } //기본 시작 좌표에 2
				System.out.println(where);
			}
		}
		TListener tListener = new TListener(); 
		class KListener extends KeyAdapter{  //???????????? ???? KeyListener
			public void keyPressed(KeyEvent e) { //플레이어(거북이)의 이동 이벤트 처리 메소드
				int key = e.getKeyCode(); //방향키로 이동하도록 설정하는 메소드
				int Mcount = Integer.parseInt(MoveCount.getText());
                Mcount += 1;
                MoveCount.setText(String.valueOf(Mcount));
				switch(key) {
					case KeyEvent.VK_UP: //위쪽 방향키
                        tListener.actionPerformed(null);
                            //
							f[turH-1][turW].setIcon(turU);
                            //새 위치에 플레이어 아이콘 배치
							f[turH][turW].setIcon(backGround);
                            //기존 자리는 백그라운드 아이콘으로 대체
							turH--; //플레이어의 좌표값 수정 ->문법 오류 수정할 것

						if((f[turH-2][turW].getIcon()).equals(rab)) { //승리 조건. 만약 플레이어(거북이)와 토끼(몬스터)의 좌표가 같다면 승리
							f[rabH][rabW].setIcon(rab);
							dialog.add(button); //승리 조건 다이얼로그에 버튼 추가
							dialog.setVisible(true); //다이얼로그 보이게 만들기
						}
						break; //이후 다른 방향키에 대해서도 같은 방식
                        
					case KeyEvent.VK_DOWN:
                        tListener.actionPerformed(null);
							f[turH+1][turW].setIcon(turD);
							f[turH][turW].setIcon(backGround);
							turH++;
                        
						if((f[turH+1][turW].getIcon()).equals(rab)){
							f[rabH][rabW].setIcon(rab);
							dialog.add(button);
							dialog.setVisible(true);
						}
						break;
                        
					case KeyEvent.VK_LEFT:
                        tListener.actionPerformed(null);
							f[turH][turW-1].setIcon(turL);
							f[turH][turW].setIcon(backGround);
							turW--;
						if((f[turH][turW-1].getIcon()).equals(rab)){
							f[rabH][rabW].setIcon(rab);
							dialog.add(button);
							dialog.setVisible(true);
						}
						break;
					case KeyEvent.VK_RIGHT:
                        tListener.actionPerformed(null);
							f[turH][turW+1].setIcon(turR);
							f[turH][turW].setIcon(backGround);
							turW++;
						if((f[turH][turW+1].getIcon()).equals(rab)) {
							f[rabH][rabW].setIcon(rab);
							dialog.add(button);
							dialog.setVisible(true);
						}
						break;
				}

			}

		}
		KListener listener = new KListener(); //키 리스너 처리 메소드

		button.addActionListener(new Blistener());


		JPanel panel = new JPanel(); //메인 화면 패널
		JPanel countpanel = new JPanel();//카운트라벨 들어갈 패널
		panel.setLayout(new GridLayout(13,13)); //13x13 크기의 그리드 설정
		countpanel.setLayout(new GridLayout(13,1));
		frame.requestFocus();
		frame.addKeyListener(new KListener());


		for(int i=0; i<13; i++) {
			for(int j=0; j<13; j++) {
				f[i][j].setIcon(backGround);
				f[i][j].addKeyListener(listener);
				panel.add(f[i][j]);
			}
		}
		
		f[0][0].setIcon(backGround); f[0][1].setIcon(backGround);f[0][2].setIcon(backGround);f[0][3].setIcon(backGround);f[0][4].setIcon(backGround);f[0][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[0][7].setIcon(backGround);f[0][8].setIcon(backGround);f[0][9].setIcon(backGround);f[0][10].setIcon(backGround);f[0][11].setIcon(backGround);f[0][12].setIcon(backGround);
		f[1][0].setIcon(backGround); f[1][1].setIcon(backGround);f[1][2].setIcon(backGround);f[1][3].setIcon(backGround);f[1][4].setIcon(backGround);f[1][5].setIcon(backGround);
		f[1][6].setIcon(backGround);f[1][7].setIcon(backGround);f[1][8].setIcon(backGround);f[1][9].setIcon(backGround);f[1][10].setIcon(backGround);f[1][11].setIcon(backGround);f[1][12].setIcon(backGround);
		f[2][0].setIcon(backGround); f[2][1].setIcon(backGround);f[2][2].setIcon(backGround);f[2][3].setIcon(backGround);f[2][4].setIcon(backGround);f[2][5].setIcon(backGround);
		f[2][6].setIcon(backGround);f[2][7].setIcon(backGround);f[2][8].setIcon(backGround);f[2][9].setIcon(backGround);f[2][10].setIcon(backGround);f[2][11].setIcon(backGround);f[2][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[3][1].setIcon(backGround);f[3][2].setIcon(backGround);f[3][3].setIcon(backGround);f[3][4].setIcon(backGround);f[3][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[3][7].setIcon(backGround);f[3][8].setIcon(backGround);f[3][9].setIcon(backGround);f[3][10].setIcon(backGround);f[3][11].setIcon(backGround);f[3][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[4][1].setIcon(backGround);f[4][2].setIcon(backGround);f[4][3].setIcon(backGround);f[4][4].setIcon(backGround);f[4][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[4][7].setIcon(backGround);f[4][8].setIcon(backGround);f[4][9].setIcon(backGround);f[4][10].setIcon(backGround);f[4][11].setIcon(backGround);f[4][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[5][1].setIcon(backGround);f[5][2].setIcon(backGround);f[5][3].setIcon(backGround);f[5][4].setIcon(backGround);f[5][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[5][7].setIcon(backGround);f[5][8].setIcon(backGround);f[5][9].setIcon(backGround);f[5][10].setIcon(backGround);f[5][11].setIcon(backGround);f[5][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[6][1].setIcon(backGround);f[6][2].setIcon(backGround);f[6][3].setIcon(backGround);f[6][4].setIcon(backGround);f[6][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[6][7].setIcon(backGround);f[6][8].setIcon(backGround);f[6][9].setIcon(backGround);f[6][10].setIcon(backGround);f[6][11].setIcon(backGround);f[6][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[7][1].setIcon(backGround);f[7][2].setIcon(backGround);f[7][3].setIcon(backGround);f[7][4].setIcon(backGround);f[7][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[7][7].setIcon(rab);f[7][8].setIcon(backGround);f[7][9].setIcon(backGround);f[7][10].setIcon(backGround);f[7][11].setIcon(backGround);f[7][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[8][1].setIcon(backGround);f[8][2].setIcon(backGround);f[8][3].setIcon(backGround);f[8][4].setIcon(backGround);f[8][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[8][7].setIcon(backGround);f[8][8].setIcon(backGround);f[8][9].setIcon(backGround);f[8][10].setIcon(backGround);f[8][11].setIcon(backGround);f[8][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[9][1].setIcon(backGround);f[9][2].setIcon(backGround);f[9][3].setIcon(backGround);f[9][4].setIcon(backGround);f[9][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[9][7].setIcon(backGround);f[9][8].setIcon(backGround);f[9][9].setIcon(backGround);f[9][10].setIcon(backGround);f[9][11].setIcon(backGround);f[9][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[10][1].setIcon(backGround);f[10][2].setIcon(backGround);f[10][3].setIcon(backGround);f[10][4].setIcon(backGround);f[10][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[10][7].setIcon(backGround);f[10][8].setIcon(backGround);f[10][9].setIcon(backGround);f[10][10].setIcon(backGround);f[10][11].setIcon(backGround);f[10][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[11][1].setIcon(backGround);f[11][2].setIcon(backGround);f[11][3].setIcon(backGround);f[11][4].setIcon(backGround);f[11][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[11][7].setIcon(backGround);f[11][8].setIcon(backGround);f[11][9].setIcon(backGround);f[11][10].setIcon(backGround);f[11][11].setIcon(backGround);f[11][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[12][1].setIcon(backGround);f[12][2].setIcon(backGround);f[12][3].setIcon(backGround);f[12][4].setIcon(backGround);f[12][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[12][7].setIcon(turD);f[12][8].setIcon(backGround);f[12][9].setIcon(backGround);f[12][10].setIcon(backGround);f[12][11].setIcon(backGround);f[12][12].setIcon(backGround);
		
		countpanel.add(MoveCount,BorderLayout.CENTER);
		JPanel Allpanel = new JPanel();
		Allpanel.setLayout(new BoxLayout(Allpanel,BoxLayout.Y_AXIS));
		Allpanel.add(panel);
		Allpanel.add(countpanel);
		frame.add(Allpanel); //프레임에 패널 추가
		frame.setTitle("Go! Go! Turtle!"); //게임 타이틀
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //정상 종료 옵션
		frame.setVisible(true); //메인 화면이니 이게 가장 먼저 보여야 함
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); //프레임 사이즈 설정
	}

	private static Random random; //토끼는 랜덤으로 움직여야 하기에 랜덤 변수 설정
	private static int turH, turW, rabH, rabW, where, start; //시작 좌표
    private static int turU, turD, turR, turL, rab, backGround; //플레이어, 토끼, 배경 변수
    //turU, turD, turR, turL, rab, backGround -> 등장? 인물 변수
	private static int fieldMin=0, fieldMax=13; //필드 사이즈에 따른 최솟값
	private static Icon temp1, temp2, temp3, temp4, temp;

	private static final int FRAME_WIDTH = 650; //너비 660
	private static final int FRAME_HEIGHT = 710; //높이 650
}