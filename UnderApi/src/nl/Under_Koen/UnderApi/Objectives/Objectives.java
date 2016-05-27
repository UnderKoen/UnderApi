package nl.Under_Koen.UnderApi.Objectives;

public enum Objectives implements Objective {
	HEAL{
		@Override
		public String getType() {
			return "health";
		}
	},
	DUMMY {
		@Override
		public String getType() {
			return "dummy";
		}
	},
	DEATHS{
		@Override
		public String getType() {
			return "deathCount";
		}
	},
	KILLS{
		@Override
		public String getType() {
			return "totalKillCount";
		}
	},
	PLAYERKILLS{
		@Override
		public String getType() {
			return "playerKillCount";
		}
	},
	XPLEVEL{
		@Override
		public String getType() {
			return "level";
		}
	},
	FOOD{
		@Override
		public String getType() {
			return "food";
		}
	},
	AIR{
		@Override
		public String getType() {
			return "air";
		}
	},
	ARMOR{
		@Override
		public String getType() {
			return "armor";
		}
	};
	
	@Override
	public int getSpecialId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void register() {
	}

	@Override
	public boolean isRegistered() {
		return true;
	}
}
