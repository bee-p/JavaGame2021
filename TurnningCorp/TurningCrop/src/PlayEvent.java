import java.util.Scanner;

/*
 * object: 방 내부에 있는 대표적 사물들 (부장 책상, 정돈된 책상 ,,,)
 * object 안의 item: 실제 아이템... (책상 위의 메모지, 일기장, 사탕 껍데기 ,,,)
 */

/*
 * 오기입 관련해서는 익셉션 처리로 넘길 수도 있음...아직 고민중
 * 또한 일정 부분 메소드로 따로 만들어 처리할 수도 있음(반복되는 부분)
 */

public class PlayEvent {
	// 스캐너 객체, 선택지/오브젝트 임시 저장 변수 생성
	private Scanner scan = new Scanner(System.in);
	private int num;
	private MapObject mapObject;
	
	// 타이틀 이동 판별
	private boolean goTitle;
	
	// 엔딩 판별할 때 사용하는 포인트 값
	private int endingPoint = 0;
	
	// Player, Map 객체
	// -> GameManager 클래스에서 PlayEvent 객체 생성할 때 집어넣기
	private Map[][] map;
	private Player player;
	
	
	// 생성자
	PlayEvent()
	{
		// 임시,,
	}
	PlayEvent(Map[][] map, Player player)
	{
		this.map = map;
		this.player = player;
	}
	
	// getter, setter 메소드
	public boolean getGoTitle()
	{
		return goTitle;
	}
	public void setGoTitle(boolean goTitle)
	{
		this.goTitle = goTitle;
	}
	
	
	// 층간 이동 보조 (엘리베이터)
	private void moveFloor()
	{
		while(true)
		{
			// 층 목록 보여주기
			// ex) 1층(활성화) \n ...
			for (int i = 0; i < 5; i++)
			{
				System.out.println(map[i][0].printActive());
			}
			
			System.out.println("이동할 층의 버튼을 누르자. (숫자 입력)");
			
			num = scan.nextInt();
			
			// 만일 누른 층이 비활성화 상태라면
			if (!map[num - 1][0].getActive())
			{	
				// 이동 실패, 다시 층 목록 출력으로 돌아감
				System.out.println("버튼이 눌리지 않는다.");
				continue;
			}
			
			if (num == 1)
			{
				player.setPosID(10);	// 모두 로비로 이동하는 것이기 때문에 모든 ID의 뒷 번호는 0이다.
				break;
			}
			else if (num == 2)
			{
				player.setPosID(20);
				break;
			}
			else if (num == 3)
			{
				player.setPosID(30);
				break;
			}
			else if (num == 4)
			{
				player.setPosID(40);
				break;
			}
			else if (num == 5)
			{
				player.setPosID(50);
				break;
			}
			else if (num == 666)
			{
				System.out.println("불가능하다.");
				continue;
			}
			else
			{
				System.out.println("그런 버튼은 없다.");
				continue;
			}
		}
	}
	
	// 사물(object 출력 템플릿)
	// 해당 오브젝트를 받아와 출력함
	public void objectPrint(MapObject mapObject)
	{
		// ex) 부장님의 책상이다.
		System.out.println(mapObject.getObjectName() + "이다.");
		// 해당 오브젝트의 설명 출력
		System.out.println(mapObject.getDescription());
	}
	
	
	// ----------------------------------------------------------------- \\
	
	
	// 1층 로비 이벤트 함수
	public void playFloor1_0()
	{
		while(true)
		{
			// 1. 로비 스크립트 출력
			// 로비다!
			
			// 2. 선택지 출력
			System.out.println("1. 출입기에 사원증을 찍자.");	// 저장
			System.out.println("2. 타이틀로 나가자.");			// 타이틀로 나가는 선택지가 있어야 되는데 지문을 어떻게...적어야 할지 모르겠음..
			System.out.println("3. 다른 곳을 둘러보자.");		// 다른 방 이동
			System.out.println("4. 엘리베이터를 타자.");		// 층 이동
			
			num = scan.nextInt();
			
			if (num == 1)			// 저장
			{
				// 현재 게임 데이터 로컬에 저장
				
				System.out.println("사원증을 성공적으로 찍었다!");
			}
			else if (num == 2)		// 저장 후 타이틀로 나가기
			{
				// 1. 현재 데이터 로컬에 저장
				
				// 2. 타이틀로 나가기
				goTitle = true;
				break;
			}
			else if (num == 3)		// 다른 방으로 이동
			{
				// 영업부, 카페, 경비실이 보인다. 어디로 들어갈까?
				System.out.println("1. 영업부로 들어가자.");
				System.out.println("2. 카페로 들어가자.");
				System.out.println("3. 경비실로 들어가자.");
				System.out.println("4. 생각이 달라졌다.");		// 다시 로비 선택지 출력
				
				num = scan.nextInt();
				
				if (num == 1)	// 영업부 들어가기
				{
					player.setPosID(11);
					break;
				}
				else if (num == 2)	// 카페 들어가기
				{
					player.setPosID(12);
					break;
				}
				else if (num == 3)	// 경비실 들어가기
				{
					player.setPosID(13);
					break;
				}
				else if (num == 4)	// 다른 방으로 가지 않기(로비 스크립트/선택지 다시 출력)
				{
					continue;
				}
				else
				{
					// 그 외 오기입 처리
				}
			}
			else if (num == 4)		// 다른 층으로 이동(엘리베이터 탑승)
			{
				// 1. 보스몬스터(케로베로스) 처치 판별
				// (처치하지 않았다면) 플레이어의 평판도 검사 -> 전투/그냥 물러남
				
				// 2. (처치했다면) 층 이동 메소드 수행
				moveFloor();
				break;
			}
			else
			{
				// 그 외 오기입 처리
			}
		}
	}
	
	// 1층 영업부(방1) 이벤트 함수
	public void playFloor1_1()
	{
		// 1. 배틀....확률 돌리기
		
		// 2. 영업부 스크립트 출력...
		// 영업부다. 내가 일하는 부서라 지긋하리만치 익숙할 만한데, 묘하게 서늘한 기운이 목을 감싼다. 밤이라 그런가.
		// 앞에는 익숙한 책상 배열들이 보인다. 개중에는 눈에 띄는 책상도 심심찮게 있다.

		while(true)
		{
			System.out.println("어디를 살펴볼까?");
			
			// for문으로 해당 방의 조사 사물 배열 목록 (숫자와 함께) 출력하기..
			for (int i = 0; i < map[0][1].getAllObject().length; i++)
			{	// ex) 1. 내 책상
				System.out.println((i + 1) + ". " + map[0][1].getObject(i).getObjectName());
			}
			// [마지막 번호]. 로비로 나간다.
			System.out.println((map[0][1].getAllObject().length + 1) + ". 로비로 나간다.");
			System.out.println("(원하는 선택지의 숫자 입력)");
			
			num = scan.nextInt();
			
			// 선택지 안의 번호를 제대로 입력하지 않았을 경우(오기입)
			if (!(num >= 1 && num <= map[0][1].getAllObject().length + 1))
			{
				System.out.println("그런 선택지는 없다.");
				continue;
			}
			
			// 로비로 나가기를 선택했을 경우
			if (num == map[0][1].getAllObject().length + 1)
			{
				// 로비로 위치 재조정
				player.setPosID(10);
				break;						// 반복문 탈출! (메소드 종료)
			}
			
			
			// 선택지를 제대로 입력했을 경우 (기본 출력)
			// 1. 현재 오브젝트를 선택한 오브젝트로 설정
			mapObject = map[0][1].getObject(num - 1);
			
			// 2. 해당 오브젝트의 이름 & 설명 출력
			// ex) 부장님의 책상과 그 책상에 대한 설명 출력..
			objectPrint(mapObject);
			
			
			if (num == 1)		// 부장 책상
			{
				// 책상 위에는 메모지가 있다.
				System.out.println("\n책상 위에는 " + mapObject.getItem(0).getName() + "가 있다.");
				
				System.out.println("자세히 볼까?");
				System.out.println("1. 본다.");
				System.out.println("2. 안 본다.");
				
				num = scan.nextInt();
				
				if (num == 1)		// 본다
				{	// 메모지 내용 출력
					// 사장님 결재 서류는 이메일로 보내세요.
					System.out.println(mapObject.getItem(0).getDescription());
				}
				else if (num == 2)	// 안 본다
				{
					System.out.println("안 보기로 했다. 상사의 메모는 훔쳐보면 안 된다.");
					System.out.println("다른 책상을 살펴보자.");
				}
				else				// 오기입
				{
					System.out.println("그런 선택지는 없다.");
				}
			}
			else if (num == 2)	// 수상한 사원의 책상
			{
				while(true)
				{
					System.out.println("책상 위와 책상 아래 서랍 중 어느 곳을 먼저 볼까?");
					System.out.println("1. 책상 위를 보자.");
					System.out.println("2. 책상 아래 서랍을 보자.");
					System.out.println("3. 안 볼 것이다. (책상 목록으로 나가기)\n");
					
					num = scan.nextInt();
					
					if (num == 1)		// 책상 위 조사
					{
						// 이미 아이템을 가져갔는지 검사(일기장)
						if (player.searchItem(mapObject.getItem(0).getName()))
						{
							// 아이템을 이미 가져갔다면 다시 책상 위/책상 아래 선택지로 이동
							System.out.println("책상 위에 더이상 특이한 건 보이지 않는다.");
							continue;
						}
						
						// 아이템을 가져가지 않았다면 기존 진행 그대로..
						
						// 책상 위에는 일기장이 있다.
						System.out.println("책상 위에는 " + mapObject.getItem(0).getName() + "이 있다.");
						System.out.println("가져갈까?");
						System.out.println("1. 가져가자.");
						System.out.println("2. 가져가지 말자.");
						
						num = scan.nextInt();
						
						if (num == 1)
						{
							// 플레이어 인벤토리에 아이템 저장
							player.saveInventory(mapObject.getItem(0));
							
							System.out.println(mapObject.getItem(0).getName() + "을 가져갔다.");
						}
						else if (num == 2)
						{
							System.out.println("가져가지 않기로 했다.");
						}
						else				// 오기입
						{
							System.out.println("그런 선택지는 없다.");
						}
					}
					else if (num == 2)	// 책상 아래 서랍 조사
					{
						// '모르는 열쇠'를 갖고 있는지 검사
						// 갖고 있다면
						if (player.searchItem("모르는 열쇠"))
						{
							System.out.print("책상 서랍 안에는 ");
							
							// 해당 아이템을 갖고 갔는지 검사(쉽배악1)
							if (player.searchItem(mapObject.getItem(1).getName()))
							{
								System.out.println("더이상 아무 것도 없다.");
							}
							else	// 아직 가져가지 않았다면
							{	
								System.out.println(mapObject.getItem(1).getName() + "이 있다.");
								System.out.println("가져갈까?");
								System.out.println("1. 가져가자.");
								System.out.println("2. 가져가지 말자.");
								
								num = scan.nextInt();
								
								if (num == 1)		// 가져가자
								{
									// 플레이어 인벤토리에 아이템 저장
									player.saveInventory(mapObject.getItem(1));
									
									System.out.println(mapObject.getItem(1).getName() + "을 가져갔다.");
								}
								else if (num == 2)	// 가져가지 말자
								{
									System.out.println("가져가지 않기로 했다.");
								}
								else				// 오기입
								{
									System.out.println("그런 선택지는 없다.");
								}
							}
						}
						else	// 열쇠가 없다면
						{
							System.out.println("서랍이 열리지 않는다.");
							System.out.println("자물쇠로 단단히 잠겨있다. 열쇠가 필요할 것 같다.");
						}
					}
					else if (num == 3)	// 안 보기
					{
						System.out.println("아무리 수상하더라도 남의 책상을 보면 안 된다.");
						System.out.println("수상한 사원의 책상에서 나왔다.");
						break;		// 반복문 탈출, 책상 목록들 선택지로 이동함
					}
					else				// 오기입
					{
						System.out.println("그런 선택지는 없다.");
					}
				}
			}
			else if (num == 3)	// 어지러운 책상
			{
				// 서류더미 이름, 설명 출력
				System.out.println("책상 위에는 " + mapObject.getItem(0).getName() + "가 있다.");
				System.out.println(mapObject.getItem(0).getDescription());
				
				System.out.println("그 외에 별다른 것은 없어보인다.");
				System.out.println("사실상 책상 위가 어지러워 뭐가 뭔지 알아보기가 힘들다. 나가자.");
			}
			else if (num == 4)	// 정돈된 책상
			{
				// 다이어리가 있다!
				System.out.println("책상 위에는 " + mapObject.getItem(0).getName() + "가 있다.");
				System.out.println("자세히 볼까?");
				System.out.println("1. 본다.");
				System.out.println("2. 안 본다.");
				
				num = scan.nextInt();
				
				if (num == 1)		// 본다
				{	
					// 다이어리의 설명 출력
					System.out.println(mapObject.getItem(0).getDescription());
				}
				else if (num == 2)	// 안 본다
				{
					System.out.println("안 보기로 했다.");
					System.out.println("다른 책상들을 보러가자.");
				}
				else				// 오기입
				{
					System.out.println("그런 선택지는 없다.");
				}
			}
			else if (num == 5)	// 배부른 책상.....?
			{	
				// '사탕 껍데기' 출력
				System.out.print(mapObject.getItem(0).getName());
				
				// 안 뜯은 과자봉지를 가져갔는지 검사
				if (player.searchItem(mapObject.getItem(1).getName()))
				{
					// 가져갔다면 사탕 껍데기에 대한 이름/설명만 출력
					System.out.println("가 있다.");
					System.out.println(mapObject.getItem(0).getDescription());
				}
				else	// 안 가져갔다면
				{
					// 사탕 껍데기와 안 뜯은 과자봉지가 있다.
					System.out.println("와 " + mapObject.getItem(1).getName() + "가 있다.");
					
					System.out.println("어느 것을 먼저 볼까?");
					// 선택지 목록 출력(사탕 껍데기, 과자봉지,,,)
					for (int i = 0; i < 2; i++)
						System.out.println((i + 1) + ". " + mapObject.getItem(i).getName());
//					System.out.println("3. 안 보기");	--> 선택지 번호..유동성있게 수정 필요
					
					num = scan.nextInt();
					
					if (num == 1)		// 1. 사탕 껍데기 선택
					{
						// 사탕 껍데기 설명 출력
						System.out.println(mapObject.getItem(0).getDescription());
					}
					else if (num == 2)	// 2. 안 뜯은 과자봉지 선택
					{
						// 안 뜯은 과자봉지 설명 출력
						System.out.println(mapObject.getItem(1).getDescription());
						System.out.println("가져갈까?");
						System.out.println("1. 가져가자.");
						System.out.println("2. 냅두자.");
						
						num = scan.nextInt();
						
						if (num == 1)		// 가져가자
						{
							// 플레이어 인벤토리에 아이템 저장
							player.saveInventory(mapObject.getItem(1));
							
							System.out.println(mapObject.getItem(1).getName() + "를 챙겼다.");
						}
						else if (num == 2)	// 냅두자
						{
							System.out.println("냅두기로 했다.");
							System.out.println("다른 책상이나 둘러보자.");
						}
						else				// 오기입
						{
							System.out.println("그런 선택지는 없다.");
						}
					}
					else	// 그 외 오기입
					{
						System.out.println("선택지를 잘못 입력한 듯 하다.");
					}
				}
			}
			else if (num == 6)	// 플레이어의 책상
			{
				System.out.println("책상 서랍을 열어볼까?");
				System.out.println("1. 열어보자.");
				System.out.println("2. 열지말자.");
				
				num = scan.nextInt();
				
				if (num == 1)		// 서랍 열기
				{
					// 모르는 열쇠를 가져갔다면
					if (player.searchItem(mapObject.getItem(0).getName()))
					{
						System.out.println("아무 것도 들어있지 않다. 평소에 무언가를 좀 넣어둘 걸 그랬나보다.");
					}
					// 아직 가져가지 않았다면
					else
					{	
						// 모르는 열쇠가 들어있다.
						System.out.println(mapObject.getItem(0).getName() + "가 들어있다. 이게 뭐지?");
						System.out.println("가져가볼까?");
						System.out.println("1. 가져가자.");
						System.out.println("2. 냅두자.");
						
						num = scan.nextInt();
						
						if (num == 1)		// 가져가자
						{
							// 플레이어 인벤토리에 아이템 저장
							player.saveInventory(mapObject.getItem(0));
							
							System.out.println(mapObject.getItem(0).getName() + "를 챙겼다.");
						}
						else if (num == 2)	// 냅두자
						{
							System.out.println("냅두기로 했다.");
							System.out.println("다른 책상이나 둘러보자.");
						}
						else				// 오기입
						{
							System.out.println("그런 선택지는 없다.");
						}
					}
				}
				else if (num == 2)		// 서랍 열지 않기
				{
					System.out.println("열지 않기로 했다.");
					System.out.println("다른 책상이나 좀 더 둘러보자.");
				}
				else				// 오기입
				{
					System.out.println("그런 선택지는 없다.");
				}
			}
		}
	}
	
	// 1층 카페(방2) 이벤트 함수
	public void playFloor1_2()
	{
		
	}
	
	// 1층 경비실(방3) 이벤트 함수
	public void playFloor1_3()
	{
		
	}
	
	// 1층 화장실(posID: 14) 이벤트 함수
	public void playFloor1_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 2층 로비 이벤트 함수
	public void playFloor2_0()
	{

		while(true)
		{
			// 1. 로비 스크립트 출력
			// 식당인 2층의 로비다.
			
			// 2. 선택지 출력
			System.out.println("1. 출입기에 사원증을 찍자.");	// 저장
			System.out.println("2. 타이틀로 나가자.");		// 타이틀로 나가는 선택지가 있어야 되는데 지문을 어떻게...적어야 할지 모르겠음..
			System.out.println("3. 다른 곳을 둘러보자.");		// 다른 방 이동
			System.out.println("4. 엘리베이터를 타자.");		// 층 이동
			
			num = scan.nextInt();
			
			if (num == 1)			// 저장
			{
				// 현재 게임 데이터 로컬에 저장
				
				System.out.println("사원증을 성공적으로 찍었다!\n--저장되었습니다.--");
			}
			else if (num == 2)		// 저장 후 타이틀로 나가기
			{
				// 1. 현재 데이터 로컬에 저장
				
				// 2. 타이틀로 나가기
				goTitle = true;
				break;
			}
			else if (num == 3)		// 다른 방으로 이동
			{
				// 취식실, 영양사 사무실이 보인다. 어디로 들어갈까?
				System.out.println("1. 취식실로 들어가자.");
				System.out.println("2. 영양사 사무실로 들어가자.");
				System.out.println("3. 생각이 달라졌다.");		// 다시 로비 선택지 출력
				
				num = scan.nextInt();
				
				if (num == 1)		// 취식실 들어가기
				{
					player.setPosID(21);
					break;
				}
				else if (num == 2)	// 영양사 사무실 들어가기
				{
					player.setPosID(22);
					break;
				}
				else if (num == 3)	// 다른 방으로 가지 않기(로비 스크립트/선택지 다시 출력)
				{
					continue;
				}
				else
				{
					// 그 외 오기입 처리
				}
			}
			else if (num == 4)		// 다른 층으로 이동(엘리베이터 탑승)
			{
				moveFloor();
				break;
			}
			else
			{
				// 그 외 오기입 처리
			}
		}
	}
	
	// 2층 취식실(방1) 이벤트 함수
	public void playFloor2_1()
	{
		// 1. 배틀 시스템 확률
		
		// 2. 취식실 스크립트 출력
		// 식당의 취식실이다. 사내 식당은 맛이 없기로 유명해서, 사원 대부분이 점심은 밖에서 해결해 원래 인적이 드문 곳이다. 오늘도 별 다를 건 없어 보인다.
		// 아무도 없는 취식실에는 배식대와 테이블, 식수대가 보인다.
		
		while(true)
		{
			System.out.println("어디를 살펴볼까?");
			
			//조사 가능한 사물 배열 목록 출력
			for (int i = 0; i < map[1][1].getAllObject().length; i++)
			{
				System.out.println((i+1) + ". " + map[1][1].getObject(i).getObjectName());
			}
			//마지막 번호. 로비로 나간다.
			System.out.println((map[1][1].getAllObject().length + 1) + ". 로비로 나간다.");
			System.out.println("(조사할 선택지의 숫자 입력 : )");
			
			num = scan.nextInt():
				
			//선택지 외의 번호 입력 시
			if (!(num >= 1 && num <= map[1][1].getAllObject().length + 1))
			{
				System.out.println("그 쪽은 조사가 불가능하다.");
				continue;
			}
		
			//로비로 나가는 선택지 입력했을 경우
			if (num == map[1][1].getAllObject().length + 1)
			{
				//로비로 이동
				player.setPosID(20);
				break;		//반복문 탈출 (메소드 종료)
			}
			
			//조사 가능 선택지를 입력했을 경우 (기본 출력)
			//1. 현재 오브젝트를 선택한 오브젝트로 설정
			mapObject = map[1][1].getObject(num - 1);
			
			//2. 해당 오브젝트의 이름과 설명 출력
			objectPrint(mapObject);
			
			if (num == 1)			//테이블 조사
			//2층 1번째 방 획득 아이템 : 빵(2번째 obj의 0), 쉽배악(2번째 obj의 1), 물컵(3번쨰 obj의 0)
			{
				while(true)
				{
					System.out.println("취식실 한가운데 커다란 테이블이 있다. 어딜 살펴볼까?");
					System.out.println("1. 테이블 위를 살펴보자.");
					System.out.println("2. 테이블 아래를 살펴보자.");
					System.out.println("3. 테이블 옆 의자들을	 살펴보자.");
					System.out.println("4. 그만 살펴보자.");
					
					num = scan.nextInt();
					
					if (num == 1) 			//테이블 위를 살펴볼 경우
					{
						System.out.println("테이블 위에는 작은 화분들이 간격을 두고 놓여 있다. 조화는 아니고, 살아 있는 식물 같다.");

						//물컵을 얻었는지 확인. 물컵이 있다면~ **NPC 퀘스트
						if (player.searchItem(map[1][1].getObject(2).getItem(1).getName()))
						{
							System.out.println("마침 식수대에서 얻었던 물이 있다. 이걸로 물을 줄까?");
							System.out.println("1. 물을 준다.");					
							System.out.println("2. 물을 주지 않는다.");
							
							num = scan.nextInt();
							
							if (num == 1)
							{
								System.out.println("화분들에 물을 줬다. 식물들이 기뻐하듯 반짝거린다.");
								Player.deleteInventory(map[1][1].getObject(2).getItem(1));		//인벤토리에서 아이템(물컵) 삭제
								
								//퀘스트 완료 처리...
							}
							else if (num == 2)
							{
								System.out.println("다른 곳에 쓸 일이 있을지도 모른다. 넘어가자.");
							}
							else
							{
								System.out.println("물을 줄지, 주지 않을 지 확실히 정하자.");	
							}
						}
					}
					else if (num == 2)		//테이블 아래를 살펴볼 경우
					{
						// 이미 아이템(빵)을 가져갔는지 검사
						// 아이템을 이미 가져갔다면 
						if (player.searchItem(mapObject.getItem(0).getName()))
						{
							System.out.println("테이블 아래에 더 이상 특별한 건 보이지 않는다.");
							continue;
						}
						
						// 아이템(빵)을 가져가지 않았다면
						else {
							System.out.println("테이블 아래에 " + mapObject.getItem(0).getName() + " 하나가 있다. 누가 떨어뜨렸나?");
							
							System.out.println("가져갈까?");
							System.out.println("1. 가져가자.");
							System.out.println("2. 가져가지 말자.");
							
							num = scan.nextInt();
							
							if (num == 1)		//1. 가져간다. 를 선택했을 경우
							{
								// 플레이어 인벤토리에 아이템(빵) 저장
								player.saveInventory(mapObject.getItem(0));
								
								System.out.println("혹시 쓸 곳이 있을지도 모른다. 가져가자.\n" + mapObject.getItem(0).getName() + "을 챙겼다.");
							}
							else if (num == 2)	//2. 가져가지 않는다. 를 선택했을 경우
							{
								System.out.println("그냥 쓰레기 같다. 챙기지 말자.")
							}
							else				//선택지 외 번호 입력 시
							{
								System.out.println("어떻게 하자는 거지? 확실히 정하자.")
							}
						}
					}
					else if (num == 3)		//테이블 옆 의자들을 살펴볼 경우
					{
						System.out.println("테이블을 기준으로 의자들이 줄지어 놓여져 있다.\n둥근 의자와 네모난 의자가 섞여 놓여 있다.");
						System.out.println("배치가 신기하네. 벽 옆에 배치도가 붙어있는데, 살펴볼까?");
						System.out.println("1. 살펴본다.");
						System.out.println("2. 살펴보지 않는다.");
						
						num = scan.nextInt();
						
						if (num == 1)		//살펴볼 경우
						{
							System.out.println("--식당 취실식 배치도--");
							//ONTV
							System.out.println("ㅁㅁㅁㅁㅁ ㅁㅁㅇㅇㅁ ㅁㅁㅁㅁㅁ ㅁㅇㅇㅇㅁ");
							System.out.println("ㅁㅇㅇㅇㅁ ㅁㅇㅁㅇㅁ ㅇㅇㅁㅇㅇ ㅇㅁㅇㅁㅇ");
							System.out.println("ㅁㅁㅁㅁㅁ ㅁㅇㅇㅁㅁ ㅇㅇㅁㅇㅇ ㅇㅇㅁㅇㅇ");
						}
						else if (num == 2)	//살펴보지 않을 경우
						{
							System.out.println("중요하지 않아 보인다. 그냥 넘어가자.");
						}
						else				//선택지 외의 번호를 입력할 경우
						{
							System.out.println("볼 건지, 보지 않을 건지 확실히 하자.");
							
						}
					}
				}
			}
			else if (num == 2)		//배식대 조사
			{
				while(true)
				{
					System.out.println("배식대를 살펴봤다. \n깨끗하게 정리된 배식대에는 4개의 배식통이 올려져 있다. 열어볼까?");
					System.out.println("1. 첫 번째 통을 열어보자.");
					System.out.println("2. 두 번째 통을 열어보자.");
					System.out.println("3. 세 번째 통을 열어보자.");
					System.out.println("4. 네 번째 통을 열어보자.");
					System.out.println("5. 열어보지 말자.");
					
					num = scan.nextInt();
					
					if (num == 1)		//첫 번째 통 열어보기
					{
						System.out.println("첫 번째 통인 밥솥을 열어봤다.\n새하얀 밥이 밥솥에 담겨 있다.");
					}
					else if (num == 2)	//두 번째 통 열어보기
					{
						System.out.println("두 번째 통을 열어봤다.\n빨간 양념의 김치가 담겨 있다.");
					}
					else if (num == 3)	//세 번째 통 열어보기
					{
						System.out.println("세 번째 통을 열어봤다.\n까만 콩자반이 담겨 있다.");
					}
					else if (num == 4)	//네 번째 통 열어보기
					{
						System.out.println("마지막 통인 네 번째 통을 열어봤다.\n푸른 빛깔의 블루베리 샐러드가 담겨 있다.")
					}
					else if (num == 5)	//열어보지 않고 배식대 벗어나기
					{
						System.out.println("더 이상 살펴볼 건 없는 것 같다.");
						break;	//반복문 탈출
					}
				}
			}
			else if (num == 3)		//식수대 조사
			{
				int waterNum = 0;	//수도꼭지 돌린 순서 확인용 변수
				
				while(true)
				{
					System.out.println("식수대를 살펴봤다.\n식수대에는 색깔별로 네 개의 수도꼭지가 있다. 살펴볼까?");
					System.out.println("1. 붉은 수도꼭지를 살펴보자.");
					System.out.println("2. 푸른 수도꼭지를 살펴보자.");
					System.out.println("3. 하얀 수도꼭지를 살펴보자.");
					System.out.println("4. 까만 수도꼭지를 살펴보자.");
					System.out.println("5. 식수대 옆을 살펴보자.");
					System.out.println("6. 그만 살펴보자.");
					
					num = scan.nextInt();
					
					if (num == 1)		//붉은 수도꼭지 조사
					{
						if (waterNum == 1)		//순서가 맞을 경우 (2번째)
						{
							System.out.println("붉은 수도꼭지를 돌렸다. 물이 나온다.");
							waterNum = 2;
						}
						else if (waterNum == 0)	//첫 번째로 물을 튼 경우
						{
							System.out.println("붉은 수도꼭지를 돌렸다. 물이 나온다.")
							waterNum = 4;		//순서가 틀린 경우이므로 다음 경우에서 바로 else로 빠질 수 있도록 함.
						}
						else					//순서가 틀린 경우
						{
							System.out.println("붉은 수도꼭지를 돌리자 모든 수도꼭지에서 물이 끊겼다.");
							waterNum = 0;
						}
					}
					else if (num == 2)	//푸른 수도꼭지 조사
					{
						if (waterNum == 3)		//순서가 맞을 경우 (4번째)
						{
							//아이템(쉽배악) 가져갔는지 확인
							//가져갔을 경우
							if (player.searchItem(mapObject.getItem(0).getName()))
							{
								System.out.println("푸른 수도꼭지를 돌렸다.\n모든 물이 멈췄지만 식수대 아래에는 더 이상 아무것도 떨어지지 않았다.");
								waterNum = 0;
								continue;
							}
							//가져가지 않았을 경우
							else
							{
								//순서 올바르게 입력 -> 쉽배악 획득
								System.out.println("푸른 수도꼭지를 돌렸다.\n덜컹 하는 소리와 함께 모든 물이 멈추며 식수대 아래에서 무언가 떨어졌다.");
								waterNum = 0;
								player.saveInventory(mapObject.getItem(0));
								//쉽배악을 챙겼다.
								System.out.println("식수대 아래를 살펴보니 책 한 권이 떨어져 있다.\n" + mapObject.getItem(0).getName() + "을 챙겼다.");
							}
						}
						else if (waterNum == 0)	//첫 번째로 물을 튼 경우
						{
							System.out.println("푸른 수도꼭지를 돌렸다. 물이 나온다.");
							waterNum = 4;
						}
						else					//순서가 틀린 경우
						{
							System.out.println("푸른 수도꼭지를 돌리자 모든 수도꼭지에서 물이 끊겼다.");
							waterNum = 0;
						}
					}
					else if (num == 3)	//하얀 수도꼭지 조사
					{
						if (waterNum == 0)		//순서가 맞을 경우 (1번째)
						{
							System.out.println("하얀 수도꼭지를 돌렸다. 물이 나온다.");
							waterNum = 1;
						}
						else
						{
							System.out.println("하얀 수도꼭지를 돌리자 모든 수도꼭지에서 물이 끊겼다.");
							waterNum = 0;
						}
					}
					else if (num == 4)	//까만 수도꼭지 조사
					{
						if (waterNum == 2)		//순서가 맞을 경우 (3번째)
						{
							System.out.println("까만 수도꼭지를 돌렸다. 물이 나온다.");
							waterNum = 3;
						}
						else if (waterNum == 0)	//첫 번째로 물을 튼 경우
						{
							System.out.println("까만 수도꼭지를 돌렸다. 물이 나온다.");
							waterNum = 4;
						}
						else					//순서가 틀린 경우
						{
							System.out.println("까만 수도꼭지를 돌리자 모든 수도꼭지에서 물이 끊겼다.");
							waterNum = 0;
						}
					}
					else if (num == 5)	//식수대 옆 조사
					{
						System.out.println("식수대 옆에는 컵 소독기가 놓여 있다.");
						
						//아이템(물컵) 가져갔는지 확인
						//가져갔을 경우
						if (player.searchItem(mapObject.getItem(1).getName()))
						{
							System.out.println("더 이상 특별한 건 없어 보인다.");
							continue;
						}
						//가져가지 않았을 경우
						else
						{
							System.out.println("소독기 위에 물이 반쯤 담긴 컵이 놓여 있다. 챙길까?");
							System.out.println("1. 챙긴다.");
							System.out.println("2. 챙기지 않는다.");
							
							num = scan.nextInt();
							
							if (num == 1)		//물컵을 챙긴다
							{
								player.saveInventory(mapObject.getItem(1));
								System.out.println("혹시 필요한 일이 있을지도 모른다.\n" + mapObject.getItem(1).getName() + "을 챙겼다.");
							}
							else if (num == 2)	//물컵을 챙기지 않는다
							{
								System.out.println("굳이 챙길 필요는 없어 보인다. 그냥 두자.")	;
							}
							else				//선택지 외 번호 입력
							{
								System.out.println("챙길지 말지를 확실히 정하자.");
							}
						}
					}
				}
			}
		}
	}
	
	// 2층 영양사 사무실(방2) 이벤트 함수
	// 식당 내부에 영양사 사무실 존재
	public void playFloor2_2()
	{
		// 1. 배틀 시스템 확률
		
		// 2. 영양사 사무실 스크립트 출력
		// 회사 식당 영양사의 사무실이다. 식당 한 켠에 위치한 사무실은 단정하게 정리되어 있는 모습이다.
		// 깔끔한 책상에는 컴퓨터가 한 대 놓여 있고, 그 옆으로는 서랍장과 화분, 휴지통이 있으며 벽면에는 화이트보드가 있다.
		
		while(true)
		{
			System.out.println("어디를 살펴볼까?");
			
			//조사 가능한 사물 배열 목록 출력
			for (int i = 0; i < map[1][2].getAllObject().length; i++)
			{
				System.out.println((i+1) + ". " + map[1][2].getObject(i).getObjectName());
			}
			//마지막 번호. 로비로 나간다.
			System.out.println((map[1][2].getAllObject().length + 1) + ". 로비로 나간다.");
			System.out.println("(조사할 선택지의 숫자 입력 : )");
			
			num = scan.nextInt():
				
			//선택지 외의 번호 입력 시
			if (!(num >= 1 && num <= map[1][2].getAllObject().length + 1))
			{
				System.out.println("그 쪽은 조사가 불가능하다.");
				continue;
			}
		
			//로비로 나가는 선택지 입력했을 경우
			if (num == map[1][2].getAllObject().length + 1)
			{
				//로비로 이동
				player.setPosID(20);
				break;		//반복문 탈출 (메소드 종료)
			}
			
			//조사 가능 선택지를 입력했을 경우 (기본 출력)
			//1. 현재 오브젝트를 선택한 오브젝트로 설정
			mapObject = map[1][2].getObject(num - 1);
			
			//2. 해당 오브젝트의 이름과 설명 출력
			objectPrint(mapObject);
			
			if (num == 1)		//컴퓨터를 살펴볼 경우
			//2층 2번째 방 획득 아이템 : 쉽배악(0번째 obj의 0), 쉽배악(0번째 obj의 1), 일기장(1번째 obj의 0), 일기장(3번째 obj의 0)
			{
				System.out.println("책상 위에 있는 컴퓨터를 살펴봤다.\n전원을 켜자, 비밀번호가 걸려 있는 잠금화면이 보인다.");
				System.out.println("비밀번호 힌트 : '오랜만에 사람들과 일거리 구원!");
				System.out.println("비밀번호를 풀어볼까?");
				System.out.println("1. 풀어본다.");
				System.out.println("2. 풀지 않는다.");
				
				num = scan.nextInt();
				
				if (num == 1)		//비밀번호를 풀어 볼 경우
				{
					System.out.println("비밀번호를 풀어보자.\n비밀번호를 입력하시오 : ");
					int password = scan.nextInt();
					
					if (password == 5419)	//비밀번호를 맞게 입력할 경우
					{
						System.out.println("비밀번호가 풀렸다.")
						System.out.println("기본 화면의 배경화면이 보인다. 좀 더 살펴볼까?");
						System.out.println("1. 바탕화면을 살펴보자.");
						System.out.println("2. 휴지통을 살펴보자.");
						System.out.println("3. 뻐꾸기 파일을 살펴보자.");
						System.out.println("4. 그만 살펴보자.")
						
						num = scan.nextInt();
						
						if (num == 1)		//바탕화면을 살펴볼 경우
						{
							// 이미 아이템(쉽배악)을 가져갔는지 검사
							// 아이템을 이미 가져갔다면 
							if (player.searchItem(mapObject.getItem(0).getName()))
							{
								System.out.println("바탕화면에 더 이상 특별한 건 보이지 않는다.");
								continue;
							}
							//가져가지 않았다면
							else {
								System.out.println("바탕화면을 살펴보자.\n바로가기와 폴더들 사이 SBA_4.pdf라는 파일이 있다.");
								System.out.println("파일을 열어보자 '쉽게 배우는 악마어'라는 제목의 문서가 보인다.");
								player.saveInventory(mapObject.getItem(0));	//아이템(쉽배악)획득
							}
						}
						else if (num == 2)	//휴지통을 살펴볼 경우
						{
							// 이미 아이템(쉽배악)을 가져갔는지 검사
							// 아이템을 이미 가져갔다면 
							if (player.searchItem(mapObject.getItem(1).getName()))
							{
								System.out.println("휴지통에 더 이상 특별한 건 보이지 않는다.");
								continue;
							}
							//가져가지 않았다면
							else {
								System.out.println("휴지통을 살펴보자.\n휴지통을 누르자 SBA_5.pdf라는 파일이 남아 있는게 보인다.");
								System.out.println("복구하자 '쉽게 배우는 악마어'라는 제목의 문서가 열렸다.");
								player.saveInventory(mapObject.getItem(1));	//아이템(쉽배악)획득
							}
						}
						else if (num == 3)	//뻐꾸기 파일을 살펴볼 경우
						{
							System.out.println("뻐꾸기 파일을 살펴보자.\n폴더를 열자 5월_식단.xlsx, 6월_식단.xlsx... 등의 파일이 보인다.");
							System.out.println("파일을 열자 해당 월의 식단표가 뜬다. 한결같이 맛없고 부실한 반찬들뿐이다.");
						}
						else if (num == 4)	//그만 살펴볼 경우
						{
							System.out.println("컴퓨터는 이제 그만 살펴보자.");
						}
						else				//선택지 외의 번호를 입력할 경우
						{
							System.out.println("그 부분은 살펴볼 내용이 없다.");
						}
					}
					else					//잘못된 비밀번호를 입력했을 경우
					{
						System.out.println("비밀번호가 틀렸습니다.\n다시 생각해보자.")
					}
				}
				else if (num == 2)	//비밀번호를 풀지 않을 경우
				{
					System.out.println("컴퓨터는 그만 살펴보자.")
				}
				else				//선택지 외의 번호를 입력할 경우
				{
					System.out.println("비밀번호를 풀지, 풀지 않을지 정하자.");
				}
			}
			else if (num == 2)	//서랍장을 살펴볼 경우
			{
				System.out.println("서랍장에는 비밀번호가 걸려 있다.");
				System.out.println("번호를 누르는 형식으로 보인다. \n키패드는 아래와 같은 모양이다.\n12\n34\n56\n78\n");
				System.out.println("키패드 옆에 노란 포스트잇이 붙어 있다. '작은 것이 곧 시작이다.' 라는 내용이 적혀있다.");
				System.out.println("비밀번호를 풀어볼까?");
				System.out.println("1. 풀어본다.");
				System.out.println("2. 풀지 않는다.");
				
				num = scan.nextInt();
				
				if (num == 1)		//비밀번호를 풀어 볼 경우
				{
					System.out.println("비밀번호를 풀어보자.\n어떤 버튼을 입력할까? : ");
					int password = scan.nextInt();
					
					if (password == 13458)
					{
						// 이미 아이템(일기장)을 가져갔는지 검사
						// 아이템을 이미 가져갔다면 
						if (player.searchItem(mapObject.getItem(0).getName()))
						{
							System.out.println("서랍장에 더 이상 특별한 건 보이지 않는다.");
							continue;
						}
						//가져가지 않았다면
						else {
							System.out.println("서랍장이 열렸다.\n열어보자 안에 놓여 있는 작은 노트가 보인다.");
							System.out.println("일기장이다.");
							player.saveInventory(mapObject.getItem(0));	//아이템(일기장) 획득
						}
					}
					else
					{
						System.out.println("서랍장이 열리지 않는다.\n비밀번호가 틀린 것 같다. 다시 생각해보자.");
					}
				}
				else if (num == 2)	//비밀번호를 풀지 않을 경우
				{
					System.out.println("서랍장은 그만 살펴보자.")
				}
				else				//선택지 외의 번호를 입력할 경우
				{
					System.out.println("비밀번호를 풀지, 풀지 않을지 정하자.");
				}
			}
			else if (num == 3)	//화분을 살펴볼 경우
			{
				System.out.println("책상 옆의 창가에 잘 관리된 화분들이 놓여 있다.");
				System.out.println("화분의 배치가 들쑥날쑥한게 특이한 모양새다.");
				System.out.println("살펴볼까?");
				System.out.println("1. 살펴본다.");
				System.out.println("2. 살펴보지 않는다.");
				
				num = scan.nextInt();
				
				if (num == 1)		//살펴볼 경우
				{
					System.out.println("화분들을 자세히 살펴보았다.");
					System.out.println("- \n--\n- \n -");
					System.out.println("이러한 배치로 화분들이 놓여 있다.");
				}
				else if (num == 2)	//살펴보지 않을 경우
				{
					System.out.println("딱히 중요하지 않아 보인다. 화분은 그만 살펴보자.");
				}
				else				//선택지 외의 숫자를 입력할 경우
				{
					System.out.println("살펴볼지, 살펴보지 않을지 확실히 정하자.");
				}
			}
			else if (num == 4)	//휴지통을 살펴볼 경우
			{
				System.out.println("책상 옆에 놓인 작은 휴지통은 쓰레기가 반쯤 차있다. 더 자세히 확인해볼까?");
				System.out.println("1. 확인해본다.");
				System.out.println("2. 확인하지 않는다.");
				
				num = scan.nextInt();
				
				if (num == 1)		//확인할 경우
				{	
					// 이미 아이템(일기장)을 가져갔는지 검사
					// 아이템을 이미 가져갔다면 
					if (player.searchItem(mapObject.getItem(0).getName()))
					{
						System.out.println("휴지통에 더 이상 특별한 건 보이지 않는다.");
						continue;
					}
					//가져가지 않았다면
					else {
						System.out.println("휴지통을 더 뒤져보자 작은 책이 나왔다.");
						System.out.println("일기장이다.");
						player.saveInventory(mapObject.getItem(0));	//아이템(일기장) 획득
					}
				}
				else if (num == 2)	//확인하지 않을 경우
				{
					System.out.println("휴지통은 그만 살펴보자.");
				}
				else				//선택지 외의 숫자를 입력할 경우
				{
					System.out.println("확인할지, 확인하지 않을지 확실히 정하자.");
				}
			}
			else if (num == 5)	//화이트 보드를 살펴볼 경우
			{
				System.out.println("벽면에 걸린 화이트 보드에는 식단 일정을 비롯한 자잘한 내용들이 적혀 있다.");
				System.out.println("맛없어 보이는 식단을 비롯해서 특별한 내용은 없어 보이는데... 자세히 읽어볼까?");
				System.out.println("1. 읽어본다.");
				System.out.println("2. 읽지 않는다.");
				
				num = scan.nextInt();
				
				if (num == 1)		//읽어볼 경우
				{
					System.out.println("자세히 읽어보자.\n'화분 A의 식재료를 활용해 인간들에게 먹였더니, rnxh… enxhd… aptmRjdna… 의 반응을 보였다.'라는 내용이 적혀있다.");
					System.out.println("......무슨 말이지?");	
				}
				else if (num == 2)	//읽지 않을 경우
				{
					System.out.println("화이트 보드는 그만 살펴보자.");
				}
				else				//선택지 외의 숫자를 입력할 경우
				{
					System.out.println("읽을지, 말지 확실히 정하자.");
				}
			}
		}		
	}
	
	// 2층 화장실(posID: 24) 이벤트 함수
	public void playFloor2_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 3층 로비 이벤트 함수
	public void playFloor3_0()
	{
		
	}
	
	// 3층 개발실1(방1) 이벤트 함수
	public void playFloor3_1()
	{
		
	}
	
	// 3층 개발실2(방2) 이벤트 함수
	public void playFloor3_2()
	{
		
	}
	
	// 3층 기술부(방3) 이벤트 함수
	public void playFloor3_3()
	{
		
	}
	
	// 3층 화장실(posID: 34) 이벤트 함수
	public void playFloor3_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 4층 로비 이벤트 함수
	public void playFloor4_0()
	{
		
	}
	
	// 4층 (방1) 이벤트 함수
	public void playFloor4_1()
	{
		
	}
	
	// 4층 (방2) 이벤트 함수
	public void playFloor4_2()
	{
		
	}
	
	// 4층 (방3) 이벤트 함수
	public void playFloor4_3()
	{
		
	}
	
	// 4층 화장실(posID: 44) 이벤트 함수
	public void playFloor4_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 5층 이벤트 함수(엔딩)
	public void playFloor5()
	{
		// 엔딩화면
		// 	=> 1. 배드엔딩: 회로선을 다 모으지 못한 경우
		//	=> 2. 노말엔딩: 회로선까지 다 모아서 사장을 성불
		// 	=> 3. 진엔딩: 평판도를 다 올려서 사장식에 오름
		// 	===> 엔딩 판별은 각 아이템별로 포인트를 부여하여, 일정 포인트를 얻으면 엔딩 보기 가능
		// 	===> 포인트는 맵에서 아이템 포인트 더해주면 됨.
		
//					if(player.getCurrentRank() == 'S'){
//						// 트루엔딩
//						// 파일에서 불러오기(읽기)
//					}
//					else if(happyEndingPoint == 3){
//						//해피엔딩
//						// 파일에서 불러오기
//					}
//					else{
//						//노말 엔딩
//						// 파일에서 불러오기
//					}
	}
}
