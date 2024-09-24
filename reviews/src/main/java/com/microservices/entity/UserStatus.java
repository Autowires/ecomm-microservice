package com.microservices.entity;

public enum UserStatus {

	/**
	 * user account is not completed verification like email verification.
	 */
	PENDING_VERIFICATION,

	/**
	 * user allowed to use their account.
	 */
	ACTIVE,

	/**
	 * instead of deleting account user requested to deactivate their account
	 */
	DEACTIVATED,

	/**
	 * user account is under deletion procedure.
	 */
	DELETE_REQUEST,

	/**
	 * user account is suspended due to some reports, complaints or other reasons.
	 */
	SUSPENDED,
}